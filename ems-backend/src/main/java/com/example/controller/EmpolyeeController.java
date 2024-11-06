package com.example.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.service.EmployeeService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin("*")
@RestController
//@RequestMapping("/api/empolyees")
public class EmpolyeeController {

  @Autowired
  private EmployeeService employeeService;

  public EmpolyeeController(EmployeeService employeeService){
    this.employeeService = employeeService;
  }

  @RequestMapping(value="/api/employees", method = RequestMethod.POST)
  public String createEmpolyee(@RequestBody Employee employee){
    return employeeService.createEmployee(employee);
  }

  @RequestMapping(value = "/api/employees/{id}", method = RequestMethod.GET)  // Corrected path
  public Optional<Employee> getEmployeeById(@PathVariable Long id) {
    return employeeService.getEmployeeById(id);
  }

  @RequestMapping(value="/api/employees", method = RequestMethod.GET) // Add this line for getting all employees
  public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  @RequestMapping(value="/api/employees/{id}", method=RequestMethod.PUT) // Add this line for updating an employee
  public String updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
    return employeeService.updateEmployee(id, employeeDetails);
  }

  @RequestMapping(value="/api/employees/{id}", method=RequestMethod.DELETE) // Add this line for deleting an employee
  public String deleteEmployee(@PathVariable Long id) {
    return employeeService.deleteEmployee(id);
  }
}
