package com.begroup.beartifact.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "department")
    private String department;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "join_date")
    private LocalDate joinDate;

}
