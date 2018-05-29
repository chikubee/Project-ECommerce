package com.ecommerce.ECommerce.Service;

import com.ecommerce.ECommerce.DTO.UserDto;
import com.ecommerce.ECommerce.MailVerification.MailSender;
import com.ecommerce.ECommerce.Model.UserModel;
import com.ecommerce.ECommerce.Repository.UserRepository;
import com.ecommerce.ECommerce.UserExceptions.PasswordMismatchException;
import com.ecommerce.ECommerce.UserExceptions.UserExistsException;
import com.ecommerce.ECommerce.UserExceptions.UserNotFoundException;
import com.ecommerce.ECommerce.UserExceptions.UserNotVerifiedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    @Qualifier("MailSender")
    public MailSender mailSender;

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) throws UserExistsException {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserExistsException("User Already Existing");
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setActive(false);
        userModel.setVerificationToken(UUID.randomUUID().toString());
        userRepository.save(userModel);
        runMail(userModel.getEmail(),userModel.getVerificationToken());
        userDto.setId(userModel.getId());
        return userDto;
    }

    @Override
    public UserDto validateUser(String email, String password) throws PasswordMismatchException, UserNotVerifiedException {
        UserModel userModel = userRepository.findByEmail(email);
        if(!userModel.getActive()){
            throw new UserNotVerifiedException("User is not verified");
        }
        if(!password.equals(userModel.getPassword())){
            throw new PasswordMismatchException("passwords do not match, please try again");
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userModel, userDto);
        return userDto;
    }

    @Override
    public UserDto getUserDetails(String email) throws UserNotFoundException {
        UserModel userModel = userRepository.findByEmail(email);
        if(null == userModel){
            throw new UserNotFoundException("No such user");
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userModel, userDto);
        return userDto;
    }

    @Override
    public UserDto updateUserProfile(UserDto userDto) throws UserNotFoundException{
        return null;
    }

    private void runMail(String emailTo, String verificationToken){

        String appUrl = "10.177.1.234:8080/easybuy";


        String from = "easybuy@gmail.com";
            String to = emailTo;
            String subject = "Email VerificationToken - Easybuy";
            String body = "To confirm your e-mail address, please click the link below:\n"
                    + appUrl + "/confirm?token=" + verificationToken;;
        mailSender.sendMail(from, to, subject, body);
    }

    public String confirm(String verificationToken) {
        UserModel userModel = userRepository.findByVerificationToken(verificationToken);
        userModel.setActive(true);
        userRepository.save(userModel);
        return "Verified Successfully, Enjoy Shopping with EasyBuy :)";
    }

}
