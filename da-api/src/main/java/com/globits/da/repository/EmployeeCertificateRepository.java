package com.globits.da.repository;

import com.globits.da.domain.EmployeeCertificate;
import com.globits.da.dto.EmployeeCertificateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeCertificateRepository extends JpaRepository<EmployeeCertificate, UUID> {
    List<EmployeeCertificate> findAllByCertificateId(UUID id);

    List<EmployeeCertificate> findAllByEmployeeIdAndCertificateId(UUID id, UUID id1);

    EmployeeCertificate findByEmployeeIdAndCertificateId(UUID id, UUID id1);

    EmployeeCertificate findByEmployeeId(UUID employeeId);
}
