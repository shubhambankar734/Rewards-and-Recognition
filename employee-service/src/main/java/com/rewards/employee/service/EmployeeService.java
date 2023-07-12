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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<Object> getEmpWithAccountDetails(Long id) throws CustomException {
        log.info("Inside getEmpWithAccountDetails of Employee Service");
        EmpWAccount empWAccount = new EmpWAccount();
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Account account = restTemplate.getForObject("http://ACCOUNT-SERVICE/account/getAccount/" + employee.get().getAccountId() +
                    "", Account.class);
            empWAccount.setEmployeeDTO(employeeConverter.toEmpDto(employee.get()));
            empWAccount.setAccount(account);
            return ResponseEntity.ok(empWAccount);
        }
        throw new CustomException("Employee doesn't exist.");
    }

    public ResponseEntity<Object> fallbackEmpServiceMethod(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Account service is taking longer time than expected. Please try again later.");
    }

    public Employee saveEmployee(EmployeeDTO currentEmployeeDTO) throws CustomException {
        log.info("Saving a Employee");
        Employee employee = employeeRepository.findByEmailId(currentEmployeeDTO.getEmailId());
        if (employee != null) {
            throw new CustomException("Email Id already exist");
        }
        Employee manager = getEmployeeByEmpCode(currentEmployeeDTO.getManagerEmpCode(), false);
        Employee currentEmployee = employeeConverter.toEmpEntity(currentEmployeeDTO, manager);
        return employeeRepository.save(currentEmployee);
    }


    public Employee getEmployeeByEmpId(Long empId, boolean getManagerDetails) throws CustomException {
        Optional<Employee> employee = employeeRepository.findById(empId);
        Employee currentEmployee = employee.orElseThrow(() -> new CustomException("Employee doesn't exist."));
        return (getManagerDetails) ? currentEmployee.getManager() : currentEmployee;
    }

    public Employee getEmployeeByEmpCode(Long empCode, boolean getManagerDetails) throws CustomException {
        Employee currentEmployee = employeeRepository.findByEmpCode(empCode).
                orElseThrow(() -> new CustomException("Employee doesn't exist."));
        return (getManagerDetails) ? currentEmployee.getManager() : currentEmployee;
    }

    public List<Employee> searchEmployee(String name) {
        log.info("Searching Employee by name");
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional
    public List<Employee> saveEmployees(@RequestBody List<EmployeeDTO> employeeList) {
        log.info("Saving All Employees");
        return employeeList.stream().map(employeeDto -> {
            Employee manager = employeeRepository.findByEmpCode(employeeDto.getManagerEmpCode()).orElse(null);
            //if Employee's Manager doesnot exist in DB
            if (manager == null) {
                manager = new Employee();
                manager.setEmpCode(employeeDto.getManagerEmpCode());
                manager = employeeRepository.save(manager);
            }
            Employee employee = employeeRepository.findByEmpCode(employeeDto.getEmpCode()).orElse(null);
            //if Manager exist as Employee
            if (employee != null)
                employeeDto.setEmpId(employee.getEmpId());
            return employeeRepository.save(employeeConverter.toEmpEntity(employeeDto, manager));
        }).collect(Collectors.toList());
    }

    public List<Employee> getEmployeesByAccCode(String accountCode) throws CustomException {
        log.info("get all employees by account code");
        try {
            restTemplate.getForObject("http://ACCOUNT-SERVICE/account/getAccountByAccCode/" + accountCode, Account.class);
        } catch (IllegalStateException ise) {
            throw new CustomException("Account service is taking longer time than expected. Please try again later.");
        } catch (RestClientException rce) {
            throw new CustomException(rce.getMessage());
        }
        return employeeRepository.findByAccountCode(accountCode);
    }

}
