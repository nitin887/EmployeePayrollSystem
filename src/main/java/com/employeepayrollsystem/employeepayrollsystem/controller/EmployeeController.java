package com.employeepayrollsystem.employeepayrollsystem.controller;

import com.employeepayrollsystem.employeepayrollsystem.dto.EmployeeDTO;
import com.employeepayrollsystem.employeepayrollsystem.service.serviceinterface.EmployeeService;
import com.employeepayrollsystem.employeepayrollsystem.service.serviceinterface.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private  final EmployeeService employeeService;
    private  final PayrollService payrollService;
    //create employee
    @PostMapping
    public ResponseEntity<?> createEmployee(EmployeeDTO employeeDTO){
        EmployeeDTO createdEmployee=employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok(createdEmployee);
    }

    //update employee by id
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployeeById(Long id,EmployeeDTO employeeDTO){
        EmployeeDTO updatedEmployee=employeeService.updateEmployeeById(id,employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }
    //delete employee by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(Long id){
        String deletedEmployee=employeeService.deleteById(id);
        return ResponseEntity.ok(deletedEmployee);
    }
    //get all employee
    @GetMapping
    public ResponseEntity<?> getAllEmployee(){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }
    //get employee by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(Long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
    //get employee by name
    @GetMapping("/{name}")
    public ResponseEntity<?> getEmployeeByName(String name){
        return ResponseEntity.ok(employeeService.getEmployeeByName(name));
    }
    //get employee by email
    @GetMapping("/{email}")
    public ResponseEntity<?> getEmployeeByEmail(String email){
        return ResponseEntity.ok(employeeService.getEmployeeByEmail(email));
    }
    //get employee by joining date
    @GetMapping("/{joining_date}")
    public ResponseEntity<?> getEmployeeByJoiningDate(LocalDateTime joining_date){
        return ResponseEntity.ok(employeeService.getEmployeeByJoiningDate(joining_date));
    }
    //get employee by payroll id
    @GetMapping("/{payrollId}")
    public ResponseEntity<?> getEmployeeByPayrollId(Long payrollId){
        return ResponseEntity.ok(employeeService.getEmployeeByPayrollId(payrollId));
    }


}
