package com.wasoko.wakanda.testing.repository;

import com.wasoko.wakanda.testing.TestHelper;
import com.wasoko.wakanda.testing.dto.EmployeeDto;
import com.wasoko.wakanda.testing.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    private List<Employee> employeeList;
    @BeforeEach
    void setUp() throws IOException {
        employeeList = TestHelper.loadEmployees();
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void canCreate() {
        Employee employee = TestHelper.getRandomEmployee(employeeList);
        Employee result = this.employeeRepository.save(employee);
        assertNotNull(result);
    }
    @Test
    void canUpdate() {

    }
}