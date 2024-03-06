package com.globits.da.rest;

import com.globits.da.dto.CertificateDto;
import com.globits.da.service.impl.CertificateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

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
    @RequestMapping(value = "/showCertificate", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> showCertificate(){
        Map<String, Object> result = certificateServiceImpl.getCertificates();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteCertificateById", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCertificateById(@RequestParam ("id") UUID id){
         certificateServiceImpl.deleteCertificateById(id);
        return ResponseEntity.ok("Certificate with ID " + id + " has been deleted");
    }
}











