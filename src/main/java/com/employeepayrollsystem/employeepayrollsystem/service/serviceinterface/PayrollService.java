package com.employeepayrollsystem.employeepayrollsystem.service.serviceinterface;


import com.employeepayrollsystem.employeepayrollsystem.dto.PayrollDTO;
import com.employeepayrollsystem.employeepayrollsystem.entity.Employee;

import java.time.LocalDateTime;
import java.util.List;

public interface PayrollService {
    PayrollDTO createPayroll(PayrollDTO payrollDTO);
    PayrollDTO updatePayrollById(Long id,PayrollDTO payrollDTO);
    String deletePayrollById(Long id);
    List<PayrollDTO> getAllPayroll();
    List<PayrollDTO> getPayrollById(Long id);
    List<PayrollDTO> getPayrollByEmpId(Long empId);
    List<PayrollDTO> getPayrollByDate(LocalDateTime dateTime);
    List<PayrollDTO> getPayrollByEmployee(Employee employee);
    List<PayrollDTO> getPayrollByEmployeeId(Long enrollmentId);

}
