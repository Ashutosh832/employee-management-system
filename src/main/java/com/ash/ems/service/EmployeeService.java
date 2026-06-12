package com.ash.ems.service;

import com.ash.ems.entity.Employee;
import com.ash.ems.entity.EmployeeUpdate;
import com.ash.ems.repository.EmployeeRepository;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> get_all_Employees(){
        return employeeRepository.findAll();
    }

    public Employee create_employee(Employee employee){

        boolean exists =
                employeeRepository.existsById(employee.getId());

        if(exists){
            throw new ResponseStatusException(
                    HttpStatusCode.valueOf(409),
                    "The Employee already exists");
        }

        return employeeRepository.save(employee);
    }

    public Employee update_employee(Long id, EmployeeUpdate employee){
        Employee existingEmployee =
                employeeRepository.findById(id)
                .orElseThrow(
                    () -> new ResponseStatusException(
                        HttpStatusCode.valueOf(404),
                        "The Employee doesn't exist"));

        if(employee.getName() != null){
            existingEmployee.setName(employee.getName());
        }

        if(employee.getSalary() != null){
            existingEmployee.setSalary(employee.getSalary());
        }

        if(employee.getRole() != null){
            existingEmployee.setRole(employee.getRole());
        }

        return employeeRepository.save(existingEmployee);
    }

    public Employee searchEmployee(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(
                    () -> new ResponseStatusException(
                        HttpStatusCode.valueOf(404),
                        "The Employee doesn't exist"));
    }

    public List<Employee> searchEmployeebyname(String name){
        return employeeRepository
                .findByNameContainingIgnoreCase(name);
    }

    public boolean deleteEmployee(Long id){
        if(!employeeRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatusCode.valueOf(404),
                    "The Employee doesn't exist");
        }
        employeeRepository.deleteById(id);
        return true;
    }
}