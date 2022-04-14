package com.wasoko.wakanda.testing.service;

import com.wasoko.wakanda.testing.TestHelper;
import com.wasoko.wakanda.testing.dto.EmployeeDto;
import com.wasoko.wakanda.testing.model.Employee;
import com.wasoko.wakanda.testing.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SpringBootTest
class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeService employeeService;
    private List<Employee> employeeList;
    @BeforeEach
    void setUp() throws IOException {
         this.employeeList = TestHelper.loadEmployees();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        when(this.employeeRepository.findAll()).thenReturn(this.employeeList);
        List<EmployeeDto> employeeDtoList = this.employeeService.findAll();
        assertEquals(this.employeeList.size(), employeeDtoList.size());
    }

    @Test
    void find() {
        Employee employee = TestHelper.getRandomEmployee(this.employeeList);
        when(this.employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        EmployeeDto employeeDto = this.employeeService.find(employee.getId());
        assertEquals(employee.getId(), employeeDto.getId());
    }

    @Test
    void create() {
        Employee employee = TestHelper.getRandomEmployee(this.employeeList);
        when(this.employeeRepository.save(any(Employee.class))).thenReturn(employee);
        EmployeeDto employeeDto = EmployeeDto.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .phoneNumber(employee.getPhoneNumber())
                .build();
        employeeDto = this.employeeService.create(employeeDto);
        assertEquals(employee.getFirstName(), employeeDto.getFirstName());
    }

    @Test
    void update() {
        Employee employee = TestHelper.getRandomEmployee(this.employeeList);
        String newName = employee.getFirstName() + " -- NEW NAME";
        employee.setFirstName(newName);
        when(this.employeeRepository.save(any(Employee.class))).thenReturn(employee);
        EmployeeDto employeeDto = EmployeeDto.builder()
                .firstName(newName)
                .lastName(employee.getLastName())
                .phoneNumber(employee.getPhoneNumber())
                .build();
        employeeDto = this.employeeService.update(employeeDto);
        assertEquals(newName, employeeDto.getFirstName());
    }

    @Test
    void delete() {
    }

    @Test
    void fromDto() {
    }

    @Test
    void toDto() {
    }
}