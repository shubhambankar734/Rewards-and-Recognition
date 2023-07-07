package com.rewards.employee.converter;

import com.rewards.employee.dto.EmployeeDTO;
import com.rewards.employee.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {
    public EmployeeDTO toEmpDto(Employee employeeEntity) {
        EmployeeDTO dto = new EmployeeDTO();
        if(null != employeeEntity.getManager())
            dto.setManagerId(employeeEntity.getManager().getEmpId());
        else {
            dto.setManagerId(0l);
        }
        BeanUtils.copyProperties(employeeEntity,dto);
        return dto;
    }
    public Employee toEmpEntity(EmployeeDTO employeeDTO, Employee manager) {
        Employee employee = new Employee();
        employee.setManager(manager);
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }
}
