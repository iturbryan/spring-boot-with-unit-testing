package com.wasoko.wakanda.testing.api;

import com.wasoko.wakanda.testing.dto.EmployeeDto;
import com.wasoko.wakanda.testing.service.EmployeeService;
import com.wasoko.wakanda.testing.service.EmployeeServiceI;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeApi {
    private final EmployeeService employeeServiceI;

    public EmployeeApi(EmployeeService employeeServiceI) {
        this.employeeServiceI = employeeServiceI;
    }
    @GetMapping("/list")
    public List<EmployeeDto> get() {
        return this.employeeServiceI.findAll();
    }
    @PostMapping("/create")
    public EmployeeDto create(@RequestBody EmployeeDto employeeDto) {
        return this.employeeServiceI.create(employeeDto);
    }
    @PutMapping("/find/{id}")
    public EmployeeDto find(@PathVariable("id") long id) {
        return this.employeeServiceI.find(id);
    }
    @PatchMapping("/update")
    public EmployeeDto update(@RequestBody EmployeeDto employeeDto) {
        return this.employeeServiceI.update(employeeDto);
    }
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") long id) {
        return this.employeeServiceI.delete(id);
    }
}
