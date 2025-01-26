package com.example.dataprotection.implementationapp.serviceImplementation;

import com.example.dataprotection.implementationapp.Constants;
import com.example.dataprotection.implementationapp.model.EmployeeDetails;
import com.example.dataprotection.implementationapp.repository.EmployeeRepository;
import com.example.dataprotection.implementationapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProtectionService protectionService;

    /*
    @Autowired
    RestTemplate restTemplate;
    */

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProtectionService protectionService) {
        this.employeeRepository = employeeRepository;
        this.protectionService = protectionService;
    }

    @Override
    public List<EmployeeDetails> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeDetails save(EmployeeDetails employeeDetails) throws IllegalBlockSizeException, BadPaddingException {
        /*String[] salary = new String[1];
        salary[0] = salaryDetails.getSalary().toString();*/

        /*if((!salaryDetails.getSalary().contains(".")) || (salaryDetails.getSalary().contains(".00"))) {
            salaryDetails.setSalary(salaryDetails.getSalary()+".00");
        }*/

        /*BigDecimal encryptedSalary = new BigDecimal(encryptSalary(salaryDetails.getSalary()));
        salaryDetails.setSalary(encryptedSalary);*/

        String input = employeeDetails.getSalary();
        String modifiedSalary = "";

        if (isNumeric(input)) {
            // If the length is less than or equal to 5, add leading zeros to make the length 6
            if (input.length() <= 5) {
                // Add leading zeros
                modifiedSalary = String.format("%06d", Integer.parseInt(input));
            } else {
                throw new IllegalBlockSizeException();
            }
        } else {
            throw new NumberFormatException();
        }

        employeeDetails.setSalary(encryptSalary(modifiedSalary));
        employeeRepository.save(employeeDetails);
        return employeeDetails;
    }

    @Override
    public EmployeeDetails findById(Long id) throws IllegalBlockSizeException, BadPaddingException {
        if (employeeRepository.findById(id).isPresent()) {
//            String fakeSalary = salaryRepository.findById(id).get().getSalary();
//            BigDecimal decryptedSalary = new BigDecimal(decryptSalary(fakeSalary));
            EmployeeDetails actualDetails = employeeRepository.findById(id).get();
            String fakeSalary = actualDetails.getSalary();
            String decryptedSalary = decryptSalary(fakeSalary);
            actualDetails.setSalary(decryptedSalary);

            String modifiedDecryptedSalary = "";

            if (isNumeric(decryptedSalary)) {
                // Remove leading zeros
                modifiedDecryptedSalary = removeLeadingZeros(decryptedSalary);
            } else {
                throw new NumberFormatException();
            }

            actualDetails.setSalary(modifiedDecryptedSalary);
            return actualDetails;
        } else {
            return null;
        }
    }

    @Override
    public EmployeeDetails update(Long id, EmployeeDetails employeeDetails) throws IllegalBlockSizeException, BadPaddingException {
        EmployeeDetails actualDetails = findById(id);
        if (Objects.nonNull(employeeDetails.getName())
                && !"".equalsIgnoreCase(
                employeeDetails.getName())) {
            actualDetails.setName(employeeDetails.getName());
        }
        if (Objects.nonNull(employeeDetails.getDesignation())
                && !"".equalsIgnoreCase(
                employeeDetails.getDesignation())) {
            actualDetails.setDesignation(employeeDetails.getDesignation());
        }
        if (Objects.nonNull(employeeDetails.getSalary())
                && !"".equalsIgnoreCase(
                employeeDetails.getSalary())) {
            actualDetails.setSalary(employeeDetails.getSalary());
        }

        return save(actualDetails);
    }

    @Override
    public void delete(Long id) {
        EmployeeDetails employeeDetails = employeeRepository.findById(id).get();
        employeeRepository.delete(employeeDetails);
    }

    @Override
    public String encryptSalaryAES(String salary) {
        /*

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(contentTypePair.getKey(), contentTypePair.getValue());
        httpHeaders.set(authorizationPair.getKey(), authorizationPair.getValue());

        HttpEntity<SalaryRequestModel> httpEntity = new HttpEntity<>(new SalaryRequestModel("salary", salary), httpHeaders);
        ResponseEntity<EncodedSalary> encodedSalaryResponseBody = restTemplate.postForEntity(Constants.urlProtect, httpEntity, EncodedSalary.class);
        return Objects.requireNonNull(encodedSalaryResponseBody.getBody()).getData()[0];

        */

        String encryptedSalary = ProtectionService.encrypt(salary, Constants.secretKey, Constants.salt);
        if (encryptedSalary == null) return "Bad Input";
        return encryptedSalary;
    }

    @Override
    public String decryptSalaryAES(String salary) {
        /*

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(contentTypePair.getKey(), contentTypePair.getValue());
        httpHeaders.set(authorizationPair.getKey(), authorizationPair.getValue());

        HttpEntity<SalaryRequestModel> httpEntity = new HttpEntity<>(new SalaryRequestModel("salary", salary), httpHeaders);
        ResponseEntity<EncodedSalary> decodedSalaryResponseBody = restTemplate.postForEntity(Constants.urlAccess, httpEntity, EncodedSalary.class);
        return Objects.requireNonNull(decodedSalaryResponseBody.getBody()).getData()[0];

        */

        String decryptedSalary = ProtectionService.decrypt(salary, Constants.secretKey, Constants.salt);
        if (decryptedSalary == null) return "Bad Input";
        return decryptedSalary;
    }

    @Override
    public String encryptSalary(String plainText) throws IllegalBlockSizeException, BadPaddingException {
        return protectionService.encryptSalaryFPE(plainText);
    }

    @Override
    public String decryptSalary(String cipherText) throws IllegalBlockSizeException, BadPaddingException {
        return protectionService.decryptSalaryFPE(cipherText);
    }

    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private String removeLeadingZeros(String deccyptedSalary) {
        deccyptedSalary = deccyptedSalary.replaceFirst("^0+(?!$)", ""); // Regex explanation: ^0+ matches leading zeros (but ensures at least one digit remains)
        return deccyptedSalary;
    }
}
