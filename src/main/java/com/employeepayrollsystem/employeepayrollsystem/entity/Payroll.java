package com.employeepayrollsystem.employeepayrollsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private Double basicSalary;
    private Double taxAmount;
    private Double netSalary;

     @ManyToOne
     @JoinColumn(name = "employee_id")
     @JsonIgnore
    private Employee employee;
}
