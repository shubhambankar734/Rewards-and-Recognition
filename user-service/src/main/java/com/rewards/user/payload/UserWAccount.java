package com.rewards.user.payload;

import com.rewards.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWAccount{
    private User user;
    private Account account;
}
