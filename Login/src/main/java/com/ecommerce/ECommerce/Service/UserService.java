package com.ecommerce.ECommerce.Service;

import com.ecommerce.ECommerce.DTO.UserDto;
import com.ecommerce.ECommerce.UserExceptions.PasswordMismatchException;
import com.ecommerce.ECommerce.UserExceptions.UserExistsException;

public interface UserService {
    public UserDto createUser(UserDto userDto) throws UserExistsException;
    public UserDto validateUser(String email, String password) throws PasswordMismatchException;
}
