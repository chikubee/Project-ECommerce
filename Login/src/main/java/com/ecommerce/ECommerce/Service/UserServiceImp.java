package com.ecommerce.ECommerce.Service;

import com.ecommerce.ECommerce.DTO.UserDto;
import com.ecommerce.ECommerce.Model.UserModel;
import com.ecommerce.ECommerce.Repository.UserRepository;
import com.ecommerce.ECommerce.UserExceptions.PasswordMismatchException;
import com.ecommerce.ECommerce.UserExceptions.UserExistsException;
import com.ecommerce.ECommerce.UserSecurity.HashPassword;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public boolean createUser(UserDto userDto) throws UserExistsException {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserExistsException("User Already Existing");
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userRepository.save(userModel);
        return true;
    }

    @Override
    public UserDto validateUser(String email, String password) throws PasswordMismatchException {
        UserModel userModel = userRepository.findByEmail(email);
        if(!password.equals(userModel.getPassword())){
            throw new PasswordMismatchException("passwords do not match, please try again");
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userModel, userDto);
        return userDto;
    }

}
