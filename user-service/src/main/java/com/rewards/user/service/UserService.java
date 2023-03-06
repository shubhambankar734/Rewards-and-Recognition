package com.rewards.user.service;

import com.rewards.user.entity.User;
import com.rewards.user.payload.Account;
import com.rewards.user.payload.UserWAccount;
import com.rewards.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public UserWAccount getUser(Long id) {
        UserWAccount userWAccount = new UserWAccount();
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            Account account = restTemplate.getForObject("http://localhost:8080/account/getAccount/" + user.get().getAccountId() +
                    "", Account.class);
            userWAccount.setUser(user.get());
            userWAccount.setAccount(account);
            return userWAccount;
        }
        return null;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }


}
