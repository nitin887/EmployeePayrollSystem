package com.employeepayrollsystem.employeepayrollsystem.repository;


import com.employeepayrollsystem.employeepayrollsystem.entity.Payroll;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
 public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    List<Payroll> findByEmployee_Id(Long empId);
    List<Payroll> findByDate(LocalDateTime date);

}
