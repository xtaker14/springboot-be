package com.begroup.beartifact.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.begroup.beartifact.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
