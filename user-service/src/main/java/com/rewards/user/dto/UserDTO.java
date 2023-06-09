package com.rewards.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private long id;
    private String name;
    private String passward;
    private long accountId;
    private String emailId;
    private long managerId;
}
