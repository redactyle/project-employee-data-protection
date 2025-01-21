package com.example.dataprotection.implementationapp.repository;

import com.example.dataprotection.implementationapp.model.SalaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<SalaryDetails, Long> {
}
