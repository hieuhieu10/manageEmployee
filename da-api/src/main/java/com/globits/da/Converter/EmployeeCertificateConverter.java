package com.globits.da.Converter;

import com.globits.da.domain.Certificate;
import com.globits.da.domain.Employee;
import com.globits.da.domain.EmployeeCertificate;

import org.springframework.stereotype.Component;

@Component
public class EmployeeCertificateConverter {
    public EmployeeCertificate toEmployeeCertificate(Employee employee, Certificate certificate){
        EmployeeCertificate employeeCertificate = new EmployeeCertificate();
        employeeCertificate.setEmployee(employee);
        employeeCertificate.setCertificate(certificate);
        return employeeCertificate;
    }
}
