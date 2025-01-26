package com.example.dataprotection.implementationapp.service;

import com.example.dataprotection.implementationapp.model.EmployeeDetails;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.util.List;

public interface EmployeeService {

    List<EmployeeDetails> findAll();
    EmployeeDetails save(EmployeeDetails employeeDetails) throws IllegalBlockSizeException, BadPaddingException;
    EmployeeDetails findById(Long id) throws IllegalBlockSizeException, BadPaddingException;
    EmployeeDetails update(Long id, EmployeeDetails employeeDetails) throws IllegalBlockSizeException, BadPaddingException;
    void delete(Long id);

    String encryptSalaryAES(String salary);
    String decryptSalaryAES(String salary);

    String encryptSalary(String plainText) throws IllegalBlockSizeException, BadPaddingException;
    String decryptSalary(String cipherText) throws IllegalBlockSizeException, BadPaddingException;
}
