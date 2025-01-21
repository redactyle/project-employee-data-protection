package com.example.dataprotection.implementationapp.service;

import com.example.dataprotection.implementationapp.model.SalaryDetails;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.util.List;

public interface SalaryService {

    List<SalaryDetails> findAll();
    SalaryDetails save(SalaryDetails salaryDetails) throws IllegalBlockSizeException, BadPaddingException;
    SalaryDetails findById(Long id) throws IllegalBlockSizeException, BadPaddingException;
    SalaryDetails update(Long id, SalaryDetails salaryDetails) throws IllegalBlockSizeException, BadPaddingException;
    void delete(Long id);

    String encryptSalaryAES(String salary);
    String decryptSalaryAES(String salary);

    String encryptSalary(String plainText) throws IllegalBlockSizeException, BadPaddingException;
    String decryptSalary(String cipherText) throws IllegalBlockSizeException, BadPaddingException;
}
