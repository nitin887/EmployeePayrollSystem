package com.employeepayrollsystem.employeepayrollsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EmployeeDTO {

    private Long id;
    private String name;
    private String designation;
    private String email;
    private Double salary;
    private LocalDateTime joining_date;
    private List<PayrollDTO> payrollList;
}
