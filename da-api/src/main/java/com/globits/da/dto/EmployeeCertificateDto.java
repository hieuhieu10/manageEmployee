package com.globits.da.dto;

import com.globits.da.domain.Certificate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Component
public class EmployeeCertificateDto {

    private EmployeeDto employeeDto;
    private CertificateDto certificateDto;

//    @NotBlank(message = "validFrom is required")
    private LocalDate validFrom;

//    @NotBlank(message = "validTo is required")
    private LocalDate validTo;

    public CertificateDto getCertificateDto() {
        return certificateDto;
    }

    public void setCertificateDto(CertificateDto certificateDto) {
        this.certificateDto = certificateDto;
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }
}
