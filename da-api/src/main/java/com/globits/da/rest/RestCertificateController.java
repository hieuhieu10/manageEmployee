package com.globits.da.rest;

import com.globits.da.dto.CertificateDto;
import com.globits.da.service.impl.CertificateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/certificate")
public class RestCertificateController {

    @Autowired
    CertificateServiceImpl certificateServiceImpl;

    @RequestMapping(value = "/saveCertificate", method = RequestMethod.POST)
    public ResponseEntity<CertificateDto> saveCertificate(@RequestBody CertificateDto certificateDto){
        CertificateDto result = certificateServiceImpl.saveCertificate(certificateDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}











