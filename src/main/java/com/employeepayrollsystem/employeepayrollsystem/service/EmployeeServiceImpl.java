package com.employeepayrollsystem.employeepayrollsystem.service;

import com.employeepayrollsystem.employeepayrollsystem.dto.EmployeeDTO;
import com.employeepayrollsystem.employeepayrollsystem.entity.Employee;
import com.employeepayrollsystem.employeepayrollsystem.exception.EmployeeAlreadyExistsException;
import com.employeepayrollsystem.employeepayrollsystem.exception.EmployeeNotFoundException;
import com.employeepayrollsystem.employeepayrollsystem.mapper.EmployeeMapper;
import com.employeepayrollsystem.employeepayrollsystem.mapper.PayrollMapper;
import com.employeepayrollsystem.employeepayrollsystem.repository.EmployeeRepository;
import com.employeepayrollsystem.employeepayrollsystem.service.serviceinterface.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO.getId() != null) {
            employeeRepository.findById(employeeDTO.getId()).ifPresent(c->{
                throw  new EmployeeAlreadyExistsException("employee already found:"+employeeDTO.getId());
            });
        }
        Employee creationOfEmployee = EmployeeMapper.toEmployeeEntity(employeeDTO);
        creationOfEmployee = employeeRepository.save(creationOfEmployee);
        return EmployeeMapper.toEmployeeDTO(creationOfEmployee);
    }

    @Override
    public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employeeDTO) {
       Employee existingEmployee=employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("employee not found with :"+id));
       existingEmployee.setSalary(employeeDTO.getSalary());
       existingEmployee.setName(employeeDTO.getName());
       existingEmployee.setDesignation(employeeDTO.getDesignation());
       existingEmployee.setEmail(employeeDTO.getEmail());
       if(existingEmployee.getPayrollList().isEmpty()){
           existingEmployee.setPayrollList(employeeDTO.getPayrollList().stream().map(PayrollMapper::toPayrollEntity).collect(Collectors.toList()));

       }
       Employee updatedEmployee=employeeRepository.save(existingEmployee);
       return EmployeeMapper.toEmployeeDTO(updatedEmployee);
    }

    @Override
    public String deleteById(Long id) {
        Employee deleting =employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("employee not found"+id));
        employeeRepository.delete(deleting);

        return "employee has been deleted ";
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        return  employeeRepository.findAll().stream().map(EmployeeMapper::toEmployeeDTO).toList();
    }

    @Override
    public List<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id).stream().map(EmployeeMapper::toEmployeeDTO).toList();
    }

    @Override
    public List<EmployeeDTO> getEmployeeByName(String name) {
        return employeeRepository.findByName(name).stream().map(EmployeeMapper::toEmployeeDTO).toList();
    }

    @Override
    public List<EmployeeDTO> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email).stream().map(EmployeeMapper::toEmployeeDTO).toList();
    }

    @Override
    public List<EmployeeDTO> getEmployeeByJoiningDate(LocalDateTime joining_date) {
        return employeeRepository.findByJoiningDate(joining_date).stream().map(EmployeeMapper::toEmployeeDTO).toList();
    }

    @Override
    public List<EmployeeDTO> getEmployeeByPayrollId(Long payrollId) {
        return employeeRepository.findByPayrollList_Id(payrollId).stream().map(EmployeeMapper::toEmployeeDTO).toList();
    }
}
