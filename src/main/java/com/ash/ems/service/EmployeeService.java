package com.ash.ems.service;

import com.ash.ems.entity.Employee;
import com.ash.ems.entity.EmployeeUpdate;

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

    public Employee update_employee(Long id, EmployeeUpdate employee){
        Employee existing_Employee = employees.stream()
            .filter(emp -> emp.getId().equals(id))
            .findFirst()
            .orElseThrow(
                ()->new ResponseStatusException(HttpStatusCode.valueOf(404),"The Employee doesn't exist"));
        if(employee.getName()!= null){
            existing_Employee.setName(employee.getName());
        }
        if(employee.getSalary() != null){
            existing_Employee.setSalary(employee.getSalary());
        }
        if(employee.getRole() !=null){
            existing_Employee.setRole(employee.getRole());
        }
        return existing_Employee;
    }
    
    public Employee searchEmployee(Long id){
        Employee employee = employees.stream()
            .filter(emp -> emp.getId().equals(id))
            .findFirst()
            .orElseThrow(
                ()->new ResponseStatusException(HttpStatusCode.valueOf(404),"The Employee doesn't exist"));
        return employee;
    }

    public boolean DeleteEmployee(Long id){
        Employee existing_Employee = employees.stream().filter(emp -> emp.getId().equals(id)).findFirst().get();
        if(existing_Employee != null){
            boolean report = employees.remove(existing_Employee);
            return report;
        }
        return false;
    }
}