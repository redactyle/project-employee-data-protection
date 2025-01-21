package com.example.dataprotection.implementationapp.model.security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SalaryRequestModel {

    private String format;
    private String[] data;

    public SalaryRequestModel(String format, String[] data) {
        this.format = format;
        this.data = data;
    }
}
