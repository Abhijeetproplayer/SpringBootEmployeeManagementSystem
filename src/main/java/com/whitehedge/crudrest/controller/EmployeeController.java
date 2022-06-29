package com.whitehedge.crudrest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whitehedge.crudrest.exception.ResourceNotFoundException;
import com.whitehedge.crudrest.model.Employee;
import com.whitehedge.crudrest.repository.EmployeeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Employee Management System")

public class EmployeeController {
 
	
    @Autowired
    private EmployeeRepository employeeRepository;
    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    
    
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    
    
    @ApiOperation(value = "Get an employee by Id")
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(
    @PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ employeeId));
        return ResponseEntity.ok().body(employee);
    }
    
    
    
    @ApiOperation(value = "Add an employee")
    @PostMapping("/employees")
    public Employee createemployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
    
    
    
    @ApiOperation(value = "Update an employee")
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateemployee(
    @PathVariable(value = "id") Long employeeId,
    @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
          .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ employeeId));
  
        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setUpdatedAt(new Date());
        final Employee updatedemployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedemployee);
   }
    
    
    @ApiOperation(value = "Delete an employee")
   @DeleteMapping("/employee/{id}")
   public Map<String, Boolean> deleteemployee(
       @PathVariable(value = "id") Long employeeId) throws Exception {
       Employee employee = employeeRepository.findById(employeeId)
          .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ employeeId));

       employeeRepository.delete(employee);
       Map<String, Boolean> response = new HashMap<>();
       response.put("deleted", Boolean.TRUE);
       return response;
   }
}