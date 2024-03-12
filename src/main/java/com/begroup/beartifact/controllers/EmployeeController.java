package com.begroup.beartifact.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.time.LocalDate;

import com.begroup.beartifact.models.Employee;
import com.begroup.beartifact.services.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }

    // Content-Type application/json:
    // @PostMapping
    // public Employee createEmployee(@RequestBody Employee employee) {
    //     return employeeService.saveEmployee(employee);
    // }

    // Content-Type multipart/form-data:
    @PostMapping(consumes = "multipart/form-data")
    public Employee createEmployee(
        @RequestParam("name") String name,
        @RequestParam("department") String department,
        @RequestParam("email") String email,
        @RequestParam("salary") Double salary,
        @RequestParam("join_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate joinDate
    ) {

        Employee employee = new Employee();

        employee.setName(name);
        employee.setDepartment(department);
        employee.setEmail(email);
        employee.setSalary(salary);
        employee.setJoinDate(joinDate);
        
        return employeeService.saveEmployee(employee);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public Employee updateEmployee(
        @PathVariable Long id, 
        @ModelAttribute Employee employeeDetails
    ) {
        Employee employee = employeeService.findEmployeeById(id);

        employee.setName(employeeDetails.getName());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setEmail(employeeDetails.getEmail());
        employee.setSalary(employeeDetails.getSalary());
        employee.setJoinDate(employeeDetails.getJoinDate());

        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
