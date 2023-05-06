package com.example.voltage.clientimtation.controller;

import com.example.voltage.clientimtation.model.SalaryDetails;
import com.example.voltage.clientimtation.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1")
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @GetMapping("/salarylist")
    public ResponseEntity<List<SalaryDetails>> getSalaryList() {
        List<SalaryDetails> salaryDetailsList = salaryService.findAll();
        return new ResponseEntity<List<SalaryDetails>>(salaryDetailsList, HttpStatus.OK);
    }

    @PostMapping("/savesalary")
    public ResponseEntity<SalaryDetails> saveSalary(@RequestBody SalaryDetails salaryDetails) {
        SalaryDetails savedSalaryDetails = salaryService.save(salaryDetails);
        return new ResponseEntity<SalaryDetails>(savedSalaryDetails, HttpStatus.OK);
    }

    @GetMapping("/getsalary/{id}")
    public ResponseEntity<SalaryDetails> getSalary(@PathVariable("id") Long id) {
        SalaryDetails salaryDetails = salaryService.findById(id);
        return new ResponseEntity<SalaryDetails>(salaryDetails, HttpStatus.OK);
    }

    @PutMapping("/updatesalary/{id}")
    public ResponseEntity<SalaryDetails> updateSalary(@PathVariable("id") Long id, @RequestBody SalaryDetails salaryDetails) {
        SalaryDetails updatedSalaryDetails = salaryService.update(id, salaryDetails);
        return new ResponseEntity<SalaryDetails>(updatedSalaryDetails, HttpStatus.OK);
    }

    @DeleteMapping("/deletesalary/{id}")
    public ResponseEntity<String> deleteSalary(@PathVariable("id") Long id) {
        salaryService.delete(id);
        return new ResponseEntity<String>("Salary Record Deleted", HttpStatus.OK);
    }

    @PostMapping("/getactualsalary")
    public String getActualSalary(@RequestBody String fakeSalary) {
        String[] salary = new String[1];
        if((!fakeSalary.contains(".")) || (fakeSalary.contains(".00"))) {
            salary[0] = fakeSalary + ".00";
        }
        return salaryService.decryptSalary(salary);
    }
}
