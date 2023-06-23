package com.rewards.user.service;

import com.rewards.user.converter.UserConverter;
import com.rewards.user.dto.UserDTO;
import com.rewards.user.entity.User;
import com.rewards.user.exception.CustomException;
import com.rewards.user.payload.Account;
import com.rewards.user.payload.UserWAccount;
import com.rewards.user.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackUserServiceMethod")
    public ResponseEntity<Object> getUserWithAccountDetails(Long id) {
        log.info("Inside getUserWithAccountDetails of User Service");
        UserWAccount userWAccount = new UserWAccount();
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            Account account = restTemplate.getForObject("http://ACCOUNT-SERVICE/account/getAccount/" + user.get().getAccountId() +
                    "", Account.class);
            userWAccount.setUser(user.get());
            userWAccount.setAccount(account);
            return ResponseEntity.ok(userWAccount);
        }
        return null;
    }

    public ResponseEntity<Object> fallbackUserServiceMethod(Exception e) throws Exception {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Account service is taking longer time than expected. Please try again later.");
    }

    public User saveUser(UserDTO currentUserDTO) throws CustomException {
        log.info("Saving a User");
        User user = userRepository.findByEmailId(currentUserDTO.getEmailId());
        if (user != null) {
            throw new CustomException("Email Id already exist");
        }
        User manager = getUser(currentUserDTO.getManagerId(), false);
        User currentUser = userConverter.toUserEntity(currentUserDTO, manager);
        return userRepository.save(currentUser);
    }


    public User getUser(Long id, boolean getManagerDetails) throws CustomException {
        Optional<User> user = userRepository.findById(id);
        User currentUser = user.orElseThrow(() -> new CustomException("User doesnot exist."));
        return (getManagerDetails) ? currentUser.getManager() : currentUser;
    }

    public User searchUser(String name) {
        log.info("Searching User by name");
        Optional<User> optionalUser = userRepository.findByNameContainingIgnoreCase(name);
        return optionalUser.orElse(null);
    }
}
