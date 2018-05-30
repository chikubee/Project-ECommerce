package com.ecommerce.ECommerce.Controller;

import com.ecommerce.ECommerce.DTO.UserDto;
import com.ecommerce.ECommerce.Service.UserService;
import com.ecommerce.ECommerce.UserExceptions.PasswordMismatchException;
import com.ecommerce.ECommerce.UserExceptions.UserExistsException;
import com.ecommerce.ECommerce.UserExceptions.UserNotFoundException;
import com.ecommerce.ECommerce.UserExceptions.UserNotVerifiedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("easybuy")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserDto registerUser(@RequestBody UserDto userDto) throws UserExistsException {
        return userService.createUser(userDto);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserDto loginUser(@RequestBody UserDto userDto) throws PasswordMismatchException, UserNotVerifiedException, UserNotFoundException {
        return userService.validateUser(userDto.getEmail(), userDto.getPassword());
    }

    @RequestMapping(value = "/get-user-details", method = RequestMethod.GET)
    public UserDto getUser(@RequestParam String email) throws UserNotFoundException {
        return userService.getUserDetails(email);
    }

    @RequestMapping(value = "/update-user-profile", method = RequestMethod.POST)
    public UserDto updateUser(@RequestBody UserDto userDto) throws UserNotFoundException {
        return userService.updateUserProfile(userDto);
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirmUser(@RequestParam String token) throws UserNotFoundException {
        return userService.confirm(token);
    }


}
