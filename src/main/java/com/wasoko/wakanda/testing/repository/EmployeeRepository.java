package com.wasoko.wakanda.testing.repository;

import com.wasoko.wakanda.testing.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
