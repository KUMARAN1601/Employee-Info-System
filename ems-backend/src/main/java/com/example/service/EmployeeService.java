package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {

  @Autowired
  private final EmployeeRepository employeeRepository;

  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public String createEmployee(Employee employee){
    employeeRepository.save(employee);
    return "Added Successfully";
  }

  public Optional<Employee> getEmployeeById(Long id){
    Employee employee = employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Employee is not exist with given Id "+ id));
    return Optional.of(employee);
  }

  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll(); // This will return a list of all employees
  }

  public String updateEmployee(Long id, Employee employeeDetails) {
    Optional<Employee> employeeOptional = employeeRepository.findById(id);
    if (employeeOptional.isPresent()) {
      Employee employee = employeeOptional.get();
      employee.setFirstName(employeeDetails.getFirstName());
      employee.setLastName(employeeDetails.getLastName());
      employee.setEmail(employeeDetails.getEmail());
      employee.setPosition(employeeDetails.getPosition());
      employeeRepository.save(employee); // Save the updated employee
      return "Employee updated successfully";
    } 
    else {
      return "Employee not found";
    }
  }

  public String deleteEmployee(Long id) {
    Optional<Employee> employeeOptional = employeeRepository.findById(id);
    if (employeeOptional.isPresent()) {
      employeeRepository.delete(employeeOptional.get());
      return "Employee deleted successfully";
    } 
    else {
      return "Employee not found";
    }
  }
}
