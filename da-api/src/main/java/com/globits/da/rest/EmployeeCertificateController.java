package com.globits.da.rest;

import com.globits.da.domain.EmployeeCertificate;
import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.EmployeeCertificateDto;
import com.globits.da.service.EmployeeCertificateService;
import com.globits.da.service.impl.EmployeeCertificateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employee_certificate")
public class EmployeeCertificateController {
    @Autowired
    EmployeeCertificateServiceImpl employeeCertificateServiceImpl;

    @RequestMapping(value = "/ShowEmployeeCertificate", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeCertificate>> ShowEmployeeCertificate(){
        List<EmployeeCertificate> result = employeeCertificateServiceImpl.showAllEmployeeCertificate();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/saveEmployeeCertificate", method = RequestMethod.POST)
    public ResponseEntity<EmployeeCertificateDto> saveEmployeeCertificate(@RequestBody EmployeeCertificateDto employeeCertificateDto){
        EmployeeCertificateDto result = employeeCertificateServiceImpl.saveEmployeeCertificate(employeeCertificateDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/searchEmployeeCertificate", method = RequestMethod.GET)
    public ResponseEntity<EmployeeCertificate> searchEmployeeCertificate(@RequestParam ("employeeId") UUID employeeId){
        EmployeeCertificate result = employeeCertificateServiceImpl.searchEmployeeCertificate(employeeId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
