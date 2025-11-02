package com.employeepayrollsystem.employeepayrollsystem.mapper;

import com.employeepayrollsystem.employeepayrollsystem.dto.PayrollDTO;
import com.employeepayrollsystem.employeepayrollsystem.entity.Employee;
import com.employeepayrollsystem.employeepayrollsystem.entity.Payroll;
import org.springframework.stereotype.Component;

@Component
public class PayrollMapper {
    public static Payroll toPayrollEntity(PayrollDTO payrollDTO){
        if(payrollDTO == null){
            return  null;
        }
        Payroll payroll = new Payroll();
        payroll.setId(payrollDTO.getId());
        payroll.setDate(payrollDTO.getDate());
        payroll.setBasicSalary(payrollDTO.getBasicSalary());
        payroll.setTaxAmount(payrollDTO.getTaxAmount());
        payroll.setNetSalary(payrollDTO.getNetSalary());
        if (payrollDTO.getEmployeeId() != null) {
            Employee employee = new Employee();
            employee.setId(payrollDTO.getEmployeeId());
            payroll.setEmployee(employee);
        }
        return  payroll;
    }
    public static PayrollDTO toPayrollDTO(Payroll payroll){
        if(payroll==null){
            return null;
        }
        PayrollDTO payrollDTO = new PayrollDTO();
        payrollDTO.setDate(payroll.getDate());
        payrollDTO.setId(payroll.getId());
        payrollDTO.setTaxAmount(payroll.getTaxAmount());
        payrollDTO.setNetSalary(payroll.getNetSalary());
        if (payroll.getEmployee() != null) {
            payrollDTO.setEmployeeId(payroll.getEmployee().getId());
        }
        payrollDTO.setBasicSalary(payroll.getBasicSalary());
        return  payrollDTO;

        
    }
}