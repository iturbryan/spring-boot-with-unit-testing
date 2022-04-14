package com.wasoko.wakanda.testing.service;

import com.wasoko.wakanda.testing.dto.EmployeeDto;
import com.wasoko.wakanda.testing.model.Employee;
import com.wasoko.wakanda.testing.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements EmployeeServiceI {
    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> employeeList = new ArrayList<>();
        this.employeeRepository.findAll().forEach(employeeList::add);
        return employeeList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private Employee iFind(long id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    private Employee iCreate(EmployeeDto employeeDto) {
        return this.employeeRepository.save(this.fromDto(employeeDto));
    }
    @Override
    public EmployeeDto find(long id) {
        return this.toDto(this.iFind(id));
    }
    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        return this.toDto(this.iCreate(employeeDto));
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        return this.create(employeeDto);
    }

    @Override
    public boolean delete(long id) {
        try {
            this.employeeRepository.delete(this.iFind(id));
            return true;
        } catch (PersistenceException e) {
            return false;
        }
    }
    @Override
    public Employee fromDto(EmployeeDto employeeDto) {
       if (employeeDto == null)
           return null;
       Employee employee = new Employee();
       employee.setId(employeeDto.getId());
       employee.setFirstName(employeeDto.getFirstName());
       employee.setLastName(employeeDto.getLastName());
       employee.setPhoneNumber(employeeDto.getPhoneNumber());
       return employee;
    }
    @Override
    public EmployeeDto toDto(Employee employee) {
        return employee == null ? null : EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .phoneNumber(employee.getPhoneNumber())
                .build();
    }
}
