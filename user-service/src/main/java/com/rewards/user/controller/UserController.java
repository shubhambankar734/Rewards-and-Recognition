package com.rewards.user.controller;

import com.rewards.user.converter.UserConverter;
import com.rewards.user.dto.UserDTO;
import com.rewards.user.entity.User;
import com.rewards.user.exception.CustomException;
import com.rewards.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable Long id) throws CustomException {
        return userService.getUser(id);
    }

    @GetMapping("/getUserWithAccountDetails/{id}")
    public ResponseEntity<Object> getUserWithAccountDetails(@PathVariable Long id){
        return userService.getUserWithAccountDetails(id);
    }

    @PostMapping("/saveUser")
    public UserDTO saveUser(@RequestBody UserDTO userDTO) throws CustomException{
        User user = userService.saveUser(userDTO);
        return userConverter.toUserDto(user);
    }

    @GetMapping("searchuser/{name}")
    public User searchUser(@PathVariable String name){
        return userService.searchUser(name);
    }


}
