package com.example.dataprotection.implementationapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tbl_salary")
@Setter
@Getter
public class SalaryDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String designation;
    private String salary;
}
