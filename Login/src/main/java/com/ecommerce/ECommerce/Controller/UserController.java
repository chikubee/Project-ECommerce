package com.ecommerce.ECommerce.Controller;
import com.ecommerce.ECommerce.DTO.UserDto;
import com.ecommerce.ECommerce.Service.UserService;
import com.ecommerce.ECommerce.UserExceptions.PasswordMismatchException;
import com.ecommerce.ECommerce.UserExceptions.UserExistsException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("easybuy")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public UserDto registerUser(@RequestBody UserDto userDto) throws UserExistsException {
        return userService.createUser(userDto);
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public UserDto loginUser(@RequestBody UserDto userDto) throws PasswordMismatchException {
        return userService.validateUser(userDto.getEmail(),userDto.getPassword());
    }

}
