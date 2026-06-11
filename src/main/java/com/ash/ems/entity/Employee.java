package com.ash.ems.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Data //creates setter and getter function
@Entity
public class Employee {

    @Id
    private Long id;
    private String name;
    private Double salary;
    private Role role;
}
