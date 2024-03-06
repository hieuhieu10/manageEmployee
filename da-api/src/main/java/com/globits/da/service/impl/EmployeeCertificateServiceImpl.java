package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.*;
import com.globits.da.dto.CertificateDto;
import com.globits.da.dto.EmployeeCertificateDto;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.repository.*;
import com.globits.da.service.EmployeeCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeCertificateServiceImpl extends GenericServiceImpl<EmployeeCertificate, UUID> implements EmployeeCertificateService {

    @Autowired
    EmployeeCertificateRepository employeeCertificateRepository;
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    CommuneRepository communeRepository;
    @Autowired
    CertificateRepository certificateRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeCertificate> showAllEmployeeCertificate() {
        return employeeCertificateRepository.findAll();
    }

    @Override
    public EmployeeCertificateDto saveEmployeeCertificate(EmployeeCertificateDto employeeCertificateDto) {
        CertificateDto certificateDto = employeeCertificateDto.getCertificateDto();
        EmployeeDto employeeDto = employeeCertificateDto.getEmployeeDto();
        EmployeeCertificate employeeCertificate = new EmployeeCertificate();
        if (certificateDto.getId() != null && employeeDto.getId() != null) {

            List<EmployeeCertificate> employeeCertificates = employeeCertificateRepository.findAllByEmployeeIdAndCertificateId
                    (employeeDto.getId(), certificateDto.getId());

            Optional<Certificate> certificate = certificateRepository.findByIdAndProvinceId(
                    certificateDto.getId(), certificateDto.getProvinceId());
            Employee employee = employeeRepository.findById(employeeDto.getId()).orElse(null);

            if (!employeeCertificates.isEmpty()){
                for (EmployeeCertificate employeeCertificate1: employeeCertificates){
                    LocalDate validTo = employeeCertificate1.getValidTo();
                    LocalDate validFrom = employeeCertificate1.getValidFrom();
                    if ((certificate.isPresent() && (employeeCertificateDto.getValidFrom().isAfter(validFrom) && employeeCertificateDto.getValidTo().isBefore(validTo)))
                        || (employeeCertificates.size()>=3&& (employeeCertificateDto.getValidFrom().isAfter(validFrom) && employeeCertificateDto.getValidTo().isBefore(validTo)))){
                        throw new RuntimeException("Employee could this certificate by 1 province and <3 certificate : "
                                + employeeDto.getId() + "\n" +
                                certificateDto.getId() + "\n" +
                                employeeDto.getProvinceId());
                    }
                }
            }

            if (certificate.isPresent() && employee != null) {
                Certificate certificate1 = certificateRepository.findById(certificateDto.getId()).orElse(null);
                employeeCertificate.setCertificate(certificate1);
                employeeCertificate.setEmployee(employee);
                employeeCertificate.setValidFrom(employeeCertificateDto.getValidFrom());
                employeeCertificate.setValidTo(employeeCertificateDto.getValidTo());

            } else {
                throw new RuntimeException("not found employeeDto " +employeeDto.getId()+ "have this certificateDto "+certificateDto.getId() );
            }
        }
        employeeCertificateRepository.save(employeeCertificate);
        return employeeCertificateDto;
    }

    @Override
    public EmployeeCertificate searchEmployeeCertificate(UUID employeeId) {
        return employeeCertificateRepository.findByEmployeeId(employeeId);
    }
}
