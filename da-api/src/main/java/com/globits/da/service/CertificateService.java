package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Certificate;
import com.globits.da.domain.Province;
import com.globits.da.dto.CertificateDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public interface CertificateService extends GenericService<Certificate, UUID> {
    CertificateDto saveCertificate (CertificateDto certificateDto);
    void deleteCertificateById (UUID id);
    Map<String, Object> getCertificates();
}