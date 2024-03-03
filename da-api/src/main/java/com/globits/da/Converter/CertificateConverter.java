package com.globits.da.Converter;

import com.globits.da.domain.Certificate;
import com.globits.da.dto.CertificateDto;
import org.springframework.stereotype.Component;

@Component
public class CertificateConverter {
    public Certificate toCertificate(CertificateDto certificateDto){
        Certificate certificate = new Certificate();
        certificate.setId(certificateDto.getId());
        certificate.setName(certificateDto.getName());
        return certificate;
    }
    public CertificateDto toCertificateDto(Certificate certificate){
        CertificateDto certificateDto = new CertificateDto();
        certificateDto.setId(certificate.getId());;
        certificateDto.setName(certificate.getName());
        return certificateDto;
    }
}