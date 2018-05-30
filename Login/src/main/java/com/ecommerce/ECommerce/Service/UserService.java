package com.ecommerce.ECommerce.Service;

import com.ecommerce.ECommerce.DTO.UserDto;
import com.ecommerce.ECommerce.UserExceptions.PasswordMismatchException;
import com.ecommerce.ECommerce.UserExceptions.UserExistsException;
import com.ecommerce.ECommerce.UserExceptions.UserNotFoundException;
import com.ecommerce.ECommerce.UserExceptions.UserNotVerifiedException;

public interface UserService {
    UserDto createUser(UserDto userDto) throws UserExistsException;

    UserDto validateUser(String email, String password) throws PasswordMismatchException, UserNotVerifiedException, UserNotFoundException;

    UserDto getUserDetails(String email) throws UserNotFoundException;

    UserDto updateUserProfile(UserDto userDto) throws UserNotFoundException;

    String confirm(String token) throws UserNotFoundException;
}
