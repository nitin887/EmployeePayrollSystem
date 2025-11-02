package com.employeepayrollsystem.employeepayrollsystem.service;

import com.employeepayrollsystem.employeepayrollsystem.dto.PayrollDTO;
import com.employeepayrollsystem.employeepayrollsystem.entity.Employee;
import com.employeepayrollsystem.employeepayrollsystem.entity.Payroll;
import com.employeepayrollsystem.employeepayrollsystem.exception.PayrollAlreadyExistException;
import com.employeepayrollsystem.employeepayrollsystem.exception.PayrollNotFoundException;
import com.employeepayrollsystem.employeepayrollsystem.mapper.PayrollMapper;
import com.employeepayrollsystem.employeepayrollsystem.repository.PayrollRepository;
import com.employeepayrollsystem.employeepayrollsystem.service.serviceinterface.PayrollService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PayrollServiceImpl implements PayrollService {
    private final PayrollRepository payrollRepository;

    public PayrollServiceImpl(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }

    @Override
    public PayrollDTO createPayroll(PayrollDTO payrollDTO) {
        if (payrollDTO.getId() != null) {
            payrollRepository.findById(payrollDTO.getId()).ifPresent(n->{
                throw  new PayrollAlreadyExistException("payroll found by id"+payrollDTO.getId());
            });
        }
        Payroll creatingPayroll= PayrollMapper.toPayrollEntity(payrollDTO);
        creatingPayroll=payrollRepository.save(creatingPayroll);
        return PayrollMapper.toPayrollDTO(creatingPayroll);
    }

    @Override
    public PayrollDTO updatePayrollById(Long id, PayrollDTO payrollDTO) {
      Payroll existingPayroll =payrollRepository.findById(id).orElseThrow(()->new PayrollNotFoundException("payroll not found "+id));
     existingPayroll.setDate(payrollDTO.getDate());
     if (payrollDTO.getEmployeeId() != null) {
         Employee employee = new Employee();
         employee.setId(payrollDTO.getEmployeeId());
         existingPayroll.setEmployee(employee);
     }
     existingPayroll.setBasicSalary(payrollDTO.getBasicSalary());
     existingPayroll.setTaxAmount(payrollDTO.getTaxAmount());
     existingPayroll.setNetSalary(payrollDTO.getNetSalary());
     Payroll updatedPayroll = payrollRepository.save(existingPayroll);
     return PayrollMapper.toPayrollDTO(updatedPayroll);
    }

    @Override
    public String deletePayrollById(Long id) {
        payrollRepository.findById(id).orElseThrow(()->new PayrollNotFoundException("payroll not found with id: "+id));
        payrollRepository.deleteById(id);
        return "Payroll deleted successfully";
    }

    @Override
    public List<PayrollDTO> getAllPayroll() {
        return payrollRepository.findAll().stream()
                .map(PayrollMapper::toPayrollDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PayrollDTO> getPayrollById(Long id) {
     return payrollRepository.findById(id).stream().map(PayrollMapper::toPayrollDTO).toList();
    }

    @Override
    public List<PayrollDTO> getPayrollByEmpId(Long empId) {

        return payrollRepository.findByEmployee_Id(empId).stream()
                .map(PayrollMapper::toPayrollDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PayrollDTO> getPayrollByDate(LocalDateTime dateTime) {
        return payrollRepository.findByDate(dateTime).stream().map(PayrollMapper::toPayrollDTO).toList();
    }

    @Override
    public List<PayrollDTO> getPayrollByEmployee(Employee employee) {
        return getPayrollByEmpId(employee.getId());
    }

    @Override
    public List<PayrollDTO> getPayrollByEmployeeId(Long enrollmentId) {
        return getPayrollByEmpId(enrollmentId);
    }

}
