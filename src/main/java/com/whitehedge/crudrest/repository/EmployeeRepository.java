package com.whitehedge.crudrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whitehedge.crudrest.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}