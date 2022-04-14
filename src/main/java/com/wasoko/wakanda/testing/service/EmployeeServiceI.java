package com.wasoko.wakanda.testing.service;

import com.wasoko.wakanda.testing.dto.EmployeeDto;
import com.wasoko.wakanda.testing.model.Employee;

import java.util.List;

public interface EmployeeServiceI {
    List<EmployeeDto> findAll();
    EmployeeDto create(EmployeeDto employeeDto);
    EmployeeDto find(long id);
    EmployeeDto update(EmployeeDto employeeDto);
    boolean delete(long id);
    Employee fromDto(EmployeeDto employeeDto);
    EmployeeDto toDto(Employee employee);
}
