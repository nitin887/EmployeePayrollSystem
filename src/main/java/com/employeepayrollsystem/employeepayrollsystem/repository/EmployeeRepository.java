package com.employeepayrollsystem.employeepayrollsystem.repository;

import com.employeepayrollsystem.employeepayrollsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface  EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByName(String name);

    List<Employee> findByEmail(String email);

    List<Employee> findByJoiningDate(LocalDateTime joining_Date);

    @Query("SELECT e FROM Employee e JOIN e.payrollList p WHERE p.id = :payrollId")
    List<Employee> findByPayrollList_Id(@Param("payrollId") Long payrollId);
}
