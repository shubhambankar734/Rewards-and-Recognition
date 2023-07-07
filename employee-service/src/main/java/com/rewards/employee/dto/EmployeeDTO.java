package com.rewards.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private long empId;
    private String empCode;
    private String name;
    private String password;
    private long accountId;
    private String emailId;
    private long managerId;
}
