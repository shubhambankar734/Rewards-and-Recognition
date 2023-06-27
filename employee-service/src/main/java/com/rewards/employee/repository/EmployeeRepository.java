package com.rewards.employee.repository;

import com.rewards.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByNameContainingIgnoreCase(String name);

    Employee findByEmailId(String emailId);
}
