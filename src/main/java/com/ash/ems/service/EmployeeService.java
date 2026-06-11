package com.ash.ems.service;

import com.ash.ems.entity.Employee;
import java.util.*;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService{

    List<Employee> employees = new ArrayList<>();


    public List<Employee> get_all_Employees(){
        return employees;
    }

    public Employee create_employee(Employee employee){
        boolean exist = employees.stream().anyMatch(emp -> emp.getId().equals(employee.getId()));
        if(exist){
            throw new ResponseStatusException(HttpStatusCode.valueOf(409),"The Employee already exists");
        }
        employees.add(employee);
        return employee;
    }

    public Employee update_employee(Employee employee){
        return employee;
    }
}
