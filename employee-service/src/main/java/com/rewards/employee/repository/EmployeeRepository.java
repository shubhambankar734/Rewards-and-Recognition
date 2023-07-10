package com.rewards.employee.repository;

import com.rewards.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContainingIgnoreCase(String name);

    Employee findByEmailId(String emailId);


    Optional<Employee> findByEmpCode(long managerEmpCode);

    List<Employee> findByAccountCode(String accountCode);
}
