package com.wasoko.wakanda.testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wasoko.wakanda.testing.dto.EmployeeDto;
import com.wasoko.wakanda.testing.model.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestHelper {
    private static final ObjectMapper mapper = new ObjectMapper();
    public static int getRandomIntBetween(int max) {
        return ThreadLocalRandom.current().nextInt(0, max);
    }
    public static List<Employee> loadEmployees() throws IOException {
        return Arrays.asList(mapper.readValue(Files.readString(Path.of("./src/test/resources/employees.json")), Employee[].class));
    }
    public static List<EmployeeDto> loadEmployeeDtos() throws IOException {
        return Arrays.asList(mapper.readValue(Files.readString(Path.of("./src/test/resources/employees.json")), EmployeeDto[].class));
    }
    public static Employee getRandomEmployee(List<Employee> employeeList){
        return employeeList.get(getRandomIntBetween(employeeList.size()));
    }
    public static EmployeeDto getRandomEmployeeDto(List<EmployeeDto> employeeDtoList){
        return employeeDtoList.get(getRandomIntBetween(employeeDtoList.size()));
    }
}
