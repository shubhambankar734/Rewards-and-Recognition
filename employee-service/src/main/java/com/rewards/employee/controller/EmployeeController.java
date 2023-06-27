package com.rewards.employee.controller;

import com.rewards.employee.converter.EmployeeConverter;
import com.rewards.employee.dto.EmployeeDTO;
import com.rewards.employee.entity.Employee;
import com.rewards.employee.exception.CustomException;
import com.rewards.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeConverter employeeConverter;

    @GetMapping("/getemp/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id, @RequestParam(required = false) boolean getManagerDetails) throws CustomException {
        Employee employee = employeeService.getEmployee(id, getManagerDetails);
        return employeeConverter.toEmpDto(employee);
    }

    @GetMapping("/getempwithaccountdetails/{id}")
    public ResponseEntity<Object> getEmpWithAccountDetails(@PathVariable Long id) {
        return employeeService.getEmpWithAccountDetails(id);
    }

    @PostMapping("/saveemp")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) throws CustomException {
        Employee employee = employeeService.saveEmployee(employeeDTO);
        return employeeConverter.toEmpDto(employee);
    }

    @GetMapping("searchemp/{name}")
    public Employee searchEmployee(@PathVariable String name) {
        return employeeService.searchEmployee(name);
    }


}
