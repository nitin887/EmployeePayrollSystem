package com.employeepayrollsystem.employeepayrollsystem.service;

import com.employeepayrollsystem.employeepayrollsystem.dto.PayrollDTO;
import com.employeepayrollsystem.employeepayrollsystem.entity.Employee;
import com.employeepayrollsystem.employeepayrollsystem.entity.Payroll;
import com.employeepayrollsystem.employeepayrollsystem.exception.PayrollAlreadyExistException;
import com.employeepayrollsystem.employeepayrollsystem.exception.PayrollNotFoundException;
import com.employeepayrollsystem.employeepayrollsystem.mapper.PayrollMapper;
import com.employeepayrollsystem.employeepayrollsystem.repository.PayrollRepository;
import com.employeepayrollsystem.employeepayrollsystem.service.PayrollServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PayrollServiceTest {

    @Mock
    private PayrollRepository payrollRepository;

    @InjectMocks
    private PayrollServiceImpl payrollService;

    private Payroll payroll;
    private PayrollDTO payrollDTO;
    private Employee employee;
    private Long testId;
    private Long employeeId;

    @BeforeEach
    void setUp() {
        testId = 1L;
        employeeId = 10L;

        employee = new Employee();
        employee.setId(employeeId);

        payroll = new Payroll();
        payroll.setId(testId);
        payroll.setBasicSalary(5000.0);
        payroll.setEmployee(employee);

        payrollDTO = new PayrollDTO();
        payrollDTO.setId(testId);
        payrollDTO.setBasicSalary(5000.0);
        payrollDTO.setEmployeeId(employeeId);
    }

    @Test
    void whenCreatePayroll_shouldReturnSavedPayrollDTO() {
        // Arrange
        try (MockedStatic<PayrollMapper> mockedMapper = Mockito.mockStatic(PayrollMapper.class)) {
            mockedMapper.when(() -> PayrollMapper.toPayrollEntity(any(PayrollDTO.class))).thenReturn(payroll);
            mockedMapper.when(() -> PayrollMapper.toPayrollDTO(any(Payroll.class))).thenReturn(payrollDTO);

            when(payrollRepository.findById(any(Long.class))).thenReturn(Optional.empty());
            when(payrollRepository.save(any(Payroll.class))).thenReturn(payroll);

            // Act
            PayrollDTO result = payrollService.createPayroll(payrollDTO);

            // Assert
            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(testId);
            verify(payrollRepository).save(any(Payroll.class));
        }
    }

    @Test
    void whenCreatePayroll_withExistingId_shouldThrowException() {
        // Arrange
        when(payrollRepository.findById(testId)).thenReturn(Optional.of(payroll));

        // Act & Assert
        assertThrows(PayrollAlreadyExistException.class, () -> payrollService.createPayroll(payrollDTO));
        verify(payrollRepository, never()).save(any());
    }

    @Test
    void whenUpdatePayroll_shouldReturnUpdatedPayrollDTO() {
        // Arrange
        PayrollDTO updatedInfo = new PayrollDTO();
        updatedInfo.setBasicSalary(6000.0);

        when(payrollRepository.findById(testId)).thenReturn(Optional.of(payroll));
        when(payrollRepository.save(any(Payroll.class))).thenReturn(payroll);

        try (MockedStatic<PayrollMapper> mockedMapper = Mockito.mockStatic(PayrollMapper.class)) {
            mockedMapper.when(() -> PayrollMapper.toPayrollDTO(any(Payroll.class))).thenReturn(payrollDTO);

            // Act
            PayrollDTO result = payrollService.updatePayrollById(testId, updatedInfo);

            // Assert
            assertThat(result).isNotNull();
            verify(payrollRepository).save(payroll);
            assertThat(payroll.getBasicSalary()).isEqualTo(6000.0);
        }
    }

    @Test
    void whenUpdatePayroll_withNonExistentId_shouldThrowException() {
        // Arrange
        when(payrollRepository.findById(testId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PayrollNotFoundException.class, () -> payrollService.updatePayrollById(testId, payrollDTO));
        verify(payrollRepository, never()).save(any());
    }

    @Test
    void whenDeletePayroll_shouldCompleteSuccessfully() {
        // Arrange
        when(payrollRepository.findById(testId)).thenReturn(Optional.of(payroll));
        doNothing().when(payrollRepository).deleteById(testId);

        // Act
        String result = payrollService.deletePayrollById(testId);

        // Assert
        assertThat(result).isEqualTo("Payroll deleted successfully");
        verify(payrollRepository).deleteById(testId);
    }

    @Test
    void whenGetAllPayroll_shouldReturnListOfDTOs() {
        // Arrange
        when(payrollRepository.findAll()).thenReturn(List.of(payroll));
        try (MockedStatic<PayrollMapper> mockedMapper = Mockito.mockStatic(PayrollMapper.class)) {
            mockedMapper.when(() -> PayrollMapper.toPayrollDTO(any(Payroll.class))).thenReturn(payrollDTO);

            // Act
            List<PayrollDTO> result = payrollService.getAllPayroll();

            // Assert
            assertThat(result).isNotNull().hasSize(1);
            verify(payrollRepository).findAll();
        }
    }
}
