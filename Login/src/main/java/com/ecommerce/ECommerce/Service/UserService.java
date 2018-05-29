package com.ecommerce.ECommerce.Service;

import com.ecommerce.ECommerce.DTO.UserDto;
import com.ecommerce.ECommerce.UserExceptions.PasswordMismatchException;
import com.ecommerce.ECommerce.UserExceptions.UserExistsException;
import com.ecommerce.ECommerce.UserExceptions.UserNotFoundException;
import com.ecommerce.ECommerce.UserExceptions.UserNotVerifiedException;

public interface UserService {
    public UserDto createUser(UserDto userDto) throws UserExistsException;

    public UserDto validateUser(String email, String password) throws PasswordMismatchException, UserNotVerifiedException;

    public UserDto getUserDetails(String email) throws UserNotFoundException;

    public UserDto updateUserProfile(UserDto userDto) throws UserNotFoundException;

    public String confirm(String token);
}
