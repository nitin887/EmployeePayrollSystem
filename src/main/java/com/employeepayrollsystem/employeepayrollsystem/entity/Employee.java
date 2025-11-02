package com.employeepayrollsystem.employeepayrollsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private  String name;
    private  String designation;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private Double salary;
    @Column(unique = true, name = "joining_date")
    private LocalDateTime joiningDate;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payroll> payrollList;
}
