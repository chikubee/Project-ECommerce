package com.ecommerce.ECommerce.Service;

import com.ecommerce.ECommerce.DTO.UserDto;
import com.ecommerce.ECommerce.MailVerification.MailSender;
import com.ecommerce.ECommerce.Model.UserModel;
import com.ecommerce.ECommerce.Repository.UserRepository;
import com.ecommerce.ECommerce.UserExceptions.PasswordMismatchException;
import com.ecommerce.ECommerce.UserExceptions.UserExistsException;
import com.ecommerce.ECommerce.UserExceptions.UserNotFoundException;
import com.ecommerce.ECommerce.UserExceptions.UserNotVerifiedException;
import com.ecommerce.ECommerce.UserSecurity.HashPassword;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    @Value("${easybuy.login.uri}")
    private String easybuyURI;

    @Value("${easybuy.login.from}")
    private String easybuyMAIL;

    @Value("${easybuy.login.subject}")
    private String mailSUBJECT;

    @Autowired
    @Qualifier("MailSender")
    public MailSender mailSender;

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDto createUser(UserDto userDto) throws UserExistsException {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserExistsException("User Already Existing");
        }
        UserModel userModel = new UserModel();
        userDto.setPassword(HashPassword.MD5(userDto.getPassword()));
        BeanUtils.copyProperties(userDto, userModel);

        userModel.setActive(false);
        userModel.setVerificationToken(UUID.randomUUID().toString());
        userRepository.save(userModel);
        runMail(userModel.getEmail(), userModel.getVerificationToken());
        userDto.setId(userModel.getId());
        return userDto;
    }

    @Override
    public UserDto validateUser(String email, String password) throws PasswordMismatchException, UserNotVerifiedException, UserNotFoundException {
        Optional<UserModel> userModel = userRepository.findByEmail(email);
        if(!userModel.isPresent())
            throw new UserNotFoundException("User not found");

        if (!userModel.get().getActive()) {
            throw new UserNotVerifiedException("User is not verified");
        }

        if (!HashPassword.MD5(password).equals(userModel.get().getPassword())) {
            throw new PasswordMismatchException("passwords do not match, please try again");
        }

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userModel.get(), userDto);
        return userDto;
    }

    @Override
    public UserDto getUserDetails(String email) throws UserNotFoundException {
        Optional<UserModel> userModel = userRepository.findByEmail(email);
        if (!userModel.isPresent()) {
            throw new UserNotFoundException("No such user");
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userModel.get(), userDto);
        return userDto;
    }

    @Override
    public UserDto updateUserProfile(UserDto userDto) throws UserNotFoundException {
        return null;
    }

    private void runMail(String emailTo, String verificationToken) {
        String appUrl = easybuyURI;
        String body = "To confirm your e-mail address, please click the link below:\n"
                + appUrl + "/confirm?token=" + verificationToken;
        mailSender.sendMail(easybuyMAIL, emailTo, mailSUBJECT, body);
    }

    @Override
    public String confirm(String verificationToken) throws UserNotFoundException {
        Optional<UserModel> userModel = userRepository.findByVerificationToken(verificationToken);
        if (!userModel.isPresent()) {
            throw new UserNotFoundException("Could not find User");
        }
        userModel.get().setActive(true);
        userRepository.save(userModel.get());
        return "Verified Successfully, Enjoy Shopping with EasyBuy :)";
    }

}
