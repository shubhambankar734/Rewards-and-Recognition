package com.rewards.employee.controller;

import com.rewards.employee.converter.EmployeeConverter;
import com.rewards.employee.dto.EmployeeDTO;
import com.rewards.employee.entity.Employee;
import com.rewards.employee.exception.CustomException;
import com.rewards.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeConverter employeeConverter;

    @GetMapping("/getEmpWithId/{empId}")
    public ResponseEntity<EmployeeDTO> getEmployeeByEmpId(@PathVariable Long empId, @RequestParam(required = false) boolean getManagerDetails) throws CustomException {
        Employee employee = employeeService.getEmployeeByEmpId(empId, getManagerDetails);
        if(employee == null)
            throw new CustomException("Manager doesn't exist.");
        return new ResponseEntity<>(employeeConverter.toEmpDto(employee), HttpStatus.OK);
    }

    @GetMapping("/getEmpWithCode/{empCode}")
    public ResponseEntity<EmployeeDTO> getEmployeeByEmpCode(@PathVariable Long empCode, @RequestParam(required = false) boolean getManagerDetails) throws CustomException {
        Employee employee = employeeService.getEmployeeByEmpCode(empCode, getManagerDetails);
        if(employee == null)
            throw new CustomException("Manager doesn't exist.");
        return new ResponseEntity<>(employeeConverter.toEmpDto(employee), HttpStatus.OK);
    }

    @GetMapping("/getEmpWithAccountDetails/{id}")
    public ResponseEntity<Object> getEmpWithAccountDetails(@PathVariable Long id) throws CustomException {
        return new ResponseEntity<>(employeeService.getEmpWithAccountDetails(id), HttpStatus.OK);
    }

    @PostMapping("/saveEmp")
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) throws CustomException {
        Employee employee = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(employeeConverter.toEmpDto(employee), HttpStatus.CREATED);
    }

    @GetMapping("/searchEmp/{name}")
    public ResponseEntity<List<EmployeeDTO>> searchEmployee(@PathVariable String name) {
        return new ResponseEntity<>(employeeConverter.toEmpDtoList(employeeService.searchEmployee(name)), HttpStatus.OK);
    }

    @GetMapping("/getAllEmpWithinAccount/{accountCode}")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeesInAccount(@PathVariable String accountCode) {
        return new ResponseEntity<>(employeeConverter.toEmpDtoList(employeeService.getEmployeesByAccCode(accountCode)), HttpStatus.OK);
    }

    @PostMapping("/saveAllEmp")
    public ResponseEntity<List<Employee>> saveAllEmployees(@RequestBody List<EmployeeDTO> employees){
        return new ResponseEntity<>(employeeService.saveEmployees(employees), HttpStatus.CREATED);
    }
}
