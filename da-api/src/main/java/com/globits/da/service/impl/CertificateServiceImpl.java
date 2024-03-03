package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.Converter.CertificateConverter;
import com.globits.da.domain.Certificate;
import com.globits.da.domain.EmployeeCertificate;
import com.globits.da.domain.Province;
import com.globits.da.dto.CertificateDto;
import com.globits.da.repository.CertificateRepository;
import com.globits.da.repository.EmployeeCertificateRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CertificateServiceImpl extends GenericServiceImpl<Certificate, UUID> implements CertificateService {

    @Autowired
    CertificateRepository certificateRepository;
    @Autowired
    CertificateConverter certificateConverter;
    @Autowired
    EmployeeCertificateRepository employeeCertificateRepository;
    @Autowired
    ProvinceRepository provinceRepository;
    @Override
    public CertificateDto saveCertificate(CertificateDto certificateDto) {
        Certificate certificate = new Certificate();
        if (certificateDto.getId() != null){
            certificate = certificateRepository.findById(certificateDto.getId()).orElse(null);
            if (certificate != null){
                certificate = certificateConverter.toCertificate(certificateDto);
                certificate.setCreatedBy(certificateDto.getCreatedBy());
            }else {
                certificate = certificateConverter.toCertificate(certificateDto);
                certificate.setCreatedBy(certificateDto.getCreatedBy());
            }
            Province province = provinceRepository.findById(certificateDto.getProvinceId()).orElse(null);
            if (province != null){
                certificate.setProvince(province);
            }else {
                return null;
            }
            List<EmployeeCertificate> employeeCertificates = employeeCertificateRepository.findAllByCertificateId(certificateDto.getId());
            if(employeeCertificates != null){
                certificate.setEmployeeCertificates(employeeCertificates);
            }else {
                return null;
            }
        }
        certificateRepository.save(certificate);
        return certificateConverter.toCertificateDto(certificate);

    }

    @Override
    public void deleteCertificate(UUID id) {
        certificateRepository.deleteById(id);
    }
}