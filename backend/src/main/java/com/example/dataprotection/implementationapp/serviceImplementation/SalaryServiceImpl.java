package com.example.dataprotection.implementationapp.serviceImplementation;

import com.example.dataprotection.implementationapp.Constants;
import com.example.dataprotection.implementationapp.model.SalaryDetails;
import com.example.dataprotection.implementationapp.repository.SalaryRepository;
import com.example.dataprotection.implementationapp.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.util.List;
import java.util.Objects;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    ProtectionService protectionService;

    /*
    @Autowired
    RestTemplate restTemplate;
    */

    public SalaryServiceImpl(SalaryRepository salaryRepository, ProtectionService protectionService) {
        this.salaryRepository = salaryRepository;
        this.protectionService = protectionService;
    }

    @Override
    public List<SalaryDetails> findAll() {
        return salaryRepository.findAll();
    }

    @Override
    public SalaryDetails save(SalaryDetails salaryDetails) throws IllegalBlockSizeException, BadPaddingException {
        /*String[] salary = new String[1];
        salary[0] = salaryDetails.getSalary().toString();*/

        /*if((!salaryDetails.getSalary().contains(".")) || (salaryDetails.getSalary().contains(".00"))) {
            salaryDetails.setSalary(salaryDetails.getSalary()+".00");
        }*/

        /*BigDecimal encryptedSalary = new BigDecimal(encryptSalary(salaryDetails.getSalary()));
        salaryDetails.setSalary(encryptedSalary);*/

        String input = salaryDetails.getSalary();
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

        salaryDetails.setSalary(encryptSalary(modifiedSalary));
        salaryRepository.save(salaryDetails);
        return salaryDetails;
    }

    @Override
    public SalaryDetails findById(Long id) throws IllegalBlockSizeException, BadPaddingException {
        if (salaryRepository.findById(id).isPresent()) {
            String fakeSalary = salaryRepository.findById(id).get().getSalary();
//            BigDecimal decryptedSalary = new BigDecimal(decryptSalary(fakeSalary));
            SalaryDetails actualDetails = salaryRepository.findById(id).get();
            actualDetails.setSalary(decryptSalary(fakeSalary));
            return actualDetails;
        } else {
            return null;
        }
    }

    @Override
    public SalaryDetails update(Long id, SalaryDetails salaryDetails) throws IllegalBlockSizeException, BadPaddingException {
        SalaryDetails actualDetails = findById(id);
        if (Objects.nonNull(salaryDetails.getName())
                && !"".equalsIgnoreCase(
                salaryDetails.getName())) {
            actualDetails.setName(salaryDetails.getName());
        }
        if (Objects.nonNull(salaryDetails.getDesignation())
                && !"".equalsIgnoreCase(
                salaryDetails.getDesignation())) {
            actualDetails.setDesignation(salaryDetails.getDesignation());
        }
        if (Objects.nonNull(salaryDetails.getSalary())
                && !"".equalsIgnoreCase(
                salaryDetails.getSalary())) {
            actualDetails.setSalary(salaryDetails.getSalary());
        }

        return save(actualDetails);
    }

    @Override
    public void delete(Long id) {
        SalaryDetails salaryDetails = salaryRepository.findById(id).get();
        salaryRepository.delete(salaryDetails);
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
}
