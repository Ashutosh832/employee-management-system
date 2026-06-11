package com.ash.ems.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ash.ems.service.EmployeeService;
import com.ash.ems.entity.Employee;
import com.ash.ems.entity.EmployeeUpdate;

@RestController
@RequestMapping("/employee")
//Simple CRUD operation for EMS platform
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    //Create
    @PostMapping
    public Employee route_create_employee(@RequestBody Employee employee){
        return employeeService.create_employee(employee);
    }
    //Read
    @GetMapping
    public List<Employee> route_get_all_Employee(){
        return employeeService.get_all_Employees();
    }
    //Update
    @PatchMapping("/{id}")
    public Employee route_update_Employee(@PathVariable Long id, @RequestBody EmployeeUpdate emp){
        return employeeService.update_employee(id, emp);
    }
    //Delete
    @DeleteMapping("/{id}")
    public boolean route_delete_Employee(@PathVariable Long id){
        return employeeService.DeleteEmployee(id);
    }
}
