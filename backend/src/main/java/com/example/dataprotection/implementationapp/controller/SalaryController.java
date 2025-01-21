package com.example.dataprotection.implementationapp.controller;

import com.example.dataprotection.implementationapp.model.SalaryDetails;
import com.example.dataprotection.implementationapp.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1")
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @GetMapping("/getallemployees")
    public ResponseEntity<List<SalaryDetails>> getSalaryList() {
        List<SalaryDetails> salaryDetailsList = salaryService.findAll();
        return new ResponseEntity<>(salaryDetailsList, HttpStatus.OK);
    }

    @PostMapping("/saveemployee")
    public ResponseEntity<SalaryDetails> saveSalary(@RequestBody SalaryDetails salaryDetails) throws IllegalBlockSizeException, BadPaddingException {
        /*for (char c : salaryDetails.getSalary().toCharArray()) {
            if (!Character.isDigit(c) || !(c == '.')) return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }*/
        SalaryDetails savedSalaryDetails = salaryService.save(salaryDetails);
        return new ResponseEntity<>(savedSalaryDetails, HttpStatus.OK);
    }

    @GetMapping("/getemployee/{id}")
    public ResponseEntity<SalaryDetails> getSalary(@PathVariable("id") Long id) throws IllegalBlockSizeException, BadPaddingException {
        SalaryDetails salaryDetails = salaryService.findById(id);
        return new ResponseEntity<>(salaryDetails, HttpStatus.OK);
    }

    @PutMapping("/updateemployee/{id}")
    public ResponseEntity<SalaryDetails> updateSalary(@PathVariable("id") Long id, @RequestBody SalaryDetails salaryDetails) throws IllegalBlockSizeException, BadPaddingException {
        SalaryDetails updatedSalaryDetails = salaryService.update(id, salaryDetails);
        return new ResponseEntity<>(updatedSalaryDetails, HttpStatus.OK);
    }

    @DeleteMapping("/deleteemployee/{id}")
    public ResponseEntity<String> deleteSalary(@PathVariable("id") Long id) {
        salaryService.delete(id);
        return new ResponseEntity<>("Salary Record Deleted", HttpStatus.OK);
    }

    @PostMapping("/decryptsalary")
    public String decryptSalary(@RequestBody String fakeSalary) throws IllegalBlockSizeException, BadPaddingException {
        /*if((!fakeSalary.contains(".")) || (fakeSalary.contains(".00"))) {
            fakeSalary = fakeSalary + ".00";
        }*/
        return salaryService.decryptSalary(fakeSalary);
    }

    @PostMapping("/encryptsalary")
    public String encryptSalary(@RequestBody String actualSalary) throws IllegalBlockSizeException, BadPaddingException {
        return salaryService.encryptSalary(actualSalary);
    }
}
