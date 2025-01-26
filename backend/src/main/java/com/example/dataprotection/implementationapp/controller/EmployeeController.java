package com.example.dataprotection.implementationapp.controller;

import com.example.dataprotection.implementationapp.model.EmployeeDetails;
import com.example.dataprotection.implementationapp.service.EmployeeService;
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
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getallemployees")
    public ResponseEntity<List<EmployeeDetails>> getSalaryList() {
        List<EmployeeDetails> employeeDetailsList = employeeService.findAll();
        return new ResponseEntity<>(employeeDetailsList, HttpStatus.OK);
    }

    @PostMapping("/saveemployee")
    public ResponseEntity<EmployeeDetails> saveSalary(@RequestBody EmployeeDetails employeeDetails) throws IllegalBlockSizeException, BadPaddingException {
        /*for (char c : salaryDetails.getSalary().toCharArray()) {
            if (!Character.isDigit(c) || !(c == '.')) return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }*/
        EmployeeDetails savedEmployeeDetails = employeeService.save(employeeDetails);
        return new ResponseEntity<>(savedEmployeeDetails, HttpStatus.OK);
    }

    @GetMapping("/getemployee/{id}")
    public ResponseEntity<EmployeeDetails> getSalary(@PathVariable("id") Long id) throws IllegalBlockSizeException, BadPaddingException {
        EmployeeDetails employeeDetails = employeeService.findById(id);
        return new ResponseEntity<>(employeeDetails, HttpStatus.OK);
    }

    @PutMapping("/updateemployee/{id}")
    public ResponseEntity<EmployeeDetails> updateSalary(@PathVariable("id") Long id, @RequestBody EmployeeDetails employeeDetails) throws IllegalBlockSizeException, BadPaddingException {
        EmployeeDetails updatedEmployeeDetails = employeeService.update(id, employeeDetails);
        return new ResponseEntity<>(updatedEmployeeDetails, HttpStatus.OK);
    }

    @DeleteMapping("/deleteemployee/{id}")
    public ResponseEntity<String> deleteSalary(@PathVariable("id") Long id) {
        employeeService.delete(id);
        return new ResponseEntity<>("Salary Record Deleted", HttpStatus.OK);
    }

    @PostMapping("/decryptsalary")
    public String decryptSalary(@RequestBody String fakeSalary) throws IllegalBlockSizeException, BadPaddingException {
        /*if((!fakeSalary.contains(".")) || (fakeSalary.contains(".00"))) {
            fakeSalary = fakeSalary + ".00";
        }*/
        return employeeService.decryptSalary(fakeSalary);
    }

    @PostMapping("/encryptsalary")
    public String encryptSalary(@RequestBody String actualSalary) throws IllegalBlockSizeException, BadPaddingException {
        return employeeService.encryptSalary(actualSalary);
    }
}
