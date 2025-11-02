package com.employeepayrollsystem.employeepayrollsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PayrollDTO {

    private Long id;
    private LocalDateTime date;
    private Double basicSalary;
    private Double taxAmount;
    private Double netSalary;
    private Long employeeId;
    private EmployeeDTO employee;
}
