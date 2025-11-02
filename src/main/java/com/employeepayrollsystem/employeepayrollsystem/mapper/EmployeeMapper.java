package com.employeepayrollsystem.employeepayrollsystem.mapper;

import com.employeepayrollsystem.employeepayrollsystem.dto.EmployeeDTO;
import com.employeepayrollsystem.employeepayrollsystem.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());

        employeeDTO.setEmail(employee.getEmail());
        return employeeDTO;
    }

    public static Employee toEmployeeEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        return employee;
    }
}
