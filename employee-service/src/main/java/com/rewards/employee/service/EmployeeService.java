package com.rewards.employee.service;

import com.rewards.employee.converter.EmployeeConverter;
import com.rewards.employee.dto.EmployeeDTO;
import com.rewards.employee.entity.Employee;
import com.rewards.employee.exception.CustomException;
import com.rewards.employee.payload.Account;
import com.rewards.employee.payload.EmpWAccount;
import com.rewards.employee.repository.EmployeeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "empService", fallbackMethod = "fallbackEmpServiceMethod")
    public ResponseEntity<Object> getEmpWithAccountDetails(Long id) {
        log.info("Inside getEmpWithAccountDetails of Employee Service");
        EmpWAccount empWAccount = new EmpWAccount();
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Account account = restTemplate.getForObject("http://ACCOUNT-SERVICE/account/getAccount/" + employee.get().getAccountId() +
                    "", Account.class);
            empWAccount.setEmployee(employee.get());
            empWAccount.setAccount(account);
            return ResponseEntity.ok(empWAccount);
        }
        return null;
    }

    public ResponseEntity<Object> fallbackEmployeeServiceMethod(Exception e) throws Exception {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Account service is taking longer time than expected. Please try again later.");
    }

    public Employee saveEmployee(EmployeeDTO currentEmployeeDTO) throws CustomException {
        log.info("Saving a Employee");
        Employee employee = employeeRepository.findByEmailId(currentEmployeeDTO.getEmailId());
        if (employee != null) {
            throw new CustomException("Email Id already exist");
        }
        Employee manager = getEmployee(currentEmployeeDTO.getManagerId(), false);
        Employee currentEmployee = employeeConverter.toEmpEntity(currentEmployeeDTO, manager);
        return employeeRepository.save(currentEmployee);
    }


    public Employee getEmployee(Long id, boolean getManagerDetails) throws CustomException {
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee currentEmployee = employee.orElseThrow(() -> new CustomException("Employee doesnot exist."));
        return (getManagerDetails) ? currentEmployee.getManager() : currentEmployee;
    }

    public Employee searchEmployee(String name) {
        log.info("Searching Employee by name");
        return employeeRepository.findByNameContainingIgnoreCase(name).orElse(null);
    }

    public List<Employee> saveEmployees(@RequestBody List<Employee> employeeList){
        return employeeRepository.saveAll(employeeList);
    }
}
