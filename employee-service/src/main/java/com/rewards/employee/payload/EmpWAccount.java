package com.rewards.employee.payload;

import com.rewards.employee.dto.EmployeeDTO;
import com.rewards.employee.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpWAccount {
    private EmployeeDTO employeeDTO;
    private Account account;
}
