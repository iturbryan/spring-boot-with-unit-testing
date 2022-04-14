package com.wasoko.wakanda.testing.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wasoko.wakanda.testing.TestHelper;
import com.wasoko.wakanda.testing.dto.EmployeeDto;
import com.wasoko.wakanda.testing.model.Employee;
import com.wasoko.wakanda.testing.repository.EmployeeRepository;
import com.wasoko.wakanda.testing.service.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = EmployeeApi.class)
@ContextConfiguration(classes = {EmployeeApi.class})
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class EmployeeApiTest {
    @MockBean
    private EmployeeService employeeService;
    private List<EmployeeDto> employeeDtoList;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setup() throws IOException {
        this.employeeDtoList = TestHelper.loadEmployeeDtos();
    }
    @Test
    void canGetAll() throws Exception {
        when(this.employeeService.findAll()).thenReturn(this.employeeDtoList);
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/employees/list").accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        assertEquals(200, result.getResponse().getStatus());
        List<EmployeeDto> employeeDtos = Arrays.asList(objectMapper.readValue(result.getResponse().getContentAsString(), EmployeeDto[].class));
        assertEquals(employeeDtoList.size(), employeeDtos.size());
    }

    @Test
    void canCreate() throws Exception {
        EmployeeDto employeeDto = TestHelper.getRandomEmployeeDto(this.employeeDtoList);
        when(this.employeeService.create(employeeDto)).thenReturn(employeeDto);
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post("/employees/create").content(objectMapper.writeValueAsString(employeeDto)).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    void canFind() {
    }

    @Test
    void canUpdate() {
    }

    @Test
    void canDelete() {
    }
}