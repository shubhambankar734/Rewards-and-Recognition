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

    @GetMapping("/getuser/{id}")
    public User getUser(@PathVariable Long id, @RequestParam(required = false) boolean getManagerDetails) throws CustomException {
        return userService.getUser(id, getManagerDetails);
    }

    @GetMapping("/getuserwithaccountdetails/{id}")
    public ResponseEntity<Object> getUserWithAccountDetails(@PathVariable Long id) {
        return userService.getUserWithAccountDetails(id);
    }

    @PostMapping("/saveuser")
    public UserDTO saveUser(@RequestBody UserDTO userDTO) throws CustomException {
        User user = userService.saveUser(userDTO);
        return userConverter.toUserDto(user);
    }

    @GetMapping("searchuser/{name}")
    public User searchUser(@PathVariable String name) {
        return userService.searchUser(name);
    }


}
