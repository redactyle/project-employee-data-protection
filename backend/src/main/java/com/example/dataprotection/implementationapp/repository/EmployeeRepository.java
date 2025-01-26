package com.example.dataprotection.implementationapp.repository;

import com.example.dataprotection.implementationapp.model.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetails, Long> {
}
