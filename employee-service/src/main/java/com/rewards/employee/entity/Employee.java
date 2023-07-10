package com.rewards.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="EMPLOYEE_DETAIL")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    private Long empCode;
    private String name;
    private String password;
    private Long accountId;
    private String accountCode;
    private String emailId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="ManagerId" , referencedColumnName = "empId")
    private Employee manager;
}
