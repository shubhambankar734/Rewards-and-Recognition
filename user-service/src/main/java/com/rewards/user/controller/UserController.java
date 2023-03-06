package com.rewards.user.controller;

import com.rewards.user.entity.User;
import com.rewards.user.payload.UserWAccount;
import com.rewards.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUser/{id}")
    private UserWAccount getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping("/saveUser")
    private User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

}
