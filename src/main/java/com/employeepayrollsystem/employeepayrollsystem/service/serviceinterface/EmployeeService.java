package com.employeepayrollsystem.employeepayrollsystem.service.serviceinterface;

import com.employeepayrollsystem.employeepayrollsystem.dto.EmployeeDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employeeDTO);

    String deleteById(Long id);

    List<EmployeeDTO> getAllEmployee();

    List<EmployeeDTO> getEmployeeById(Long id);

    List<EmployeeDTO> getEmployeeByName(String name);

    List<EmployeeDTO> getEmployeeByEmail(String email);

    List<EmployeeDTO> getEmployeeByJoiningDate(LocalDateTime joining_date);

    List<EmployeeDTO> getEmployeeByPayrollId(Long payrollId);
}
