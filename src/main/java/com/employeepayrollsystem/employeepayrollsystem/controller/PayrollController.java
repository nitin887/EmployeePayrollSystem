package com.employeepayrollsystem.employeepayrollsystem.controller;

import com.employeepayrollsystem.employeepayrollsystem.dto.PayrollDTO;
import com.employeepayrollsystem.employeepayrollsystem.entity.Employee;
import com.employeepayrollsystem.employeepayrollsystem.service.serviceinterface.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/payroll")
@RequiredArgsConstructor
public class PayrollController
{
    private  final PayrollService payrollService;
  //create payroll
    @PostMapping
    public PayrollDTO createPayroll(@RequestBody PayrollDTO payrollDTO){
        return payrollService.createPayroll(payrollDTO);
    }
  //update payroll by id
    @PutMapping("/{id}")
    public PayrollDTO updatePayrollById(@PathVariable Long id,@RequestBody PayrollDTO payrollDTO) {
        return payrollService.updatePayrollById(id, payrollDTO);
    }
    //delete payroll by id
    @DeleteMapping("/{id}")
    public String deletePayrollById(@PathVariable Long id){
        return payrollService.deletePayrollById(id);
    }
    //get all payroll
    @GetMapping
    public List<PayrollDTO> getAllPayroll(){
    return payrollService.getAllPayroll();
    }
    //get payroll by id
    @GetMapping("/{id}")
    public List<PayrollDTO> getPayrollById(@PathVariable Long id){
        return payrollService.getPayrollById(id);
    }
    //get payroll by employee id
    @GetMapping("/employee/{empId}")
    public List<PayrollDTO> getPayrollByEmpId(@PathVariable Long empId){
        return payrollService.getPayrollByEmpId(empId);
    }
    //get payroll by date
    @GetMapping("/date/{date}")
    public List<PayrollDTO> getPayrollByDate(@PathVariable LocalDateTime date){
        return payrollService.getPayrollByDate(date);
    }
    //get payroll by employee
    @PostMapping("/employee-object")
    public List<PayrollDTO>  getPayrollByEmployee(@RequestBody Employee employee){
        return payrollService.getPayrollByEmployee(employee);
    }

    //get payroll by employee id
    @GetMapping("/by-employee-id/{enrollmentId}")
    public List<PayrollDTO> getPayrollByEmployeeId(@PathVariable Long enrollmentId){
        return payrollService.getPayrollByEmployeeId(enrollmentId);
    }
}
