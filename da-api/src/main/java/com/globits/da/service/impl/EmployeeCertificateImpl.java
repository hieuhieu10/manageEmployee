package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.EmployeeCertificate;
import com.globits.da.repository.EmployeeCertificateRepository;
import com.globits.da.service.EmployeeCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeCertificateImpl extends GenericServiceImpl<EmployeeCertificate, UUID> implements EmployeeCertificateService {

    @Autowired
    EmployeeCertificateRepository employeeCertificateRepository;
    @Override
    public List<EmployeeCertificate> showAllEmployeeCertificate() {
        return employeeCertificateRepository.findAll();
    }
}
