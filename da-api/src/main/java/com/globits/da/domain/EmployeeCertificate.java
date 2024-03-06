package com.globits.da.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globits.core.domain.BaseObject;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "tbl_employee_certificate")
public class EmployeeCertificate extends BaseObject {

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "certificate_id")
    @JsonIgnore
    private Certificate certificate;

    private LocalDate validFrom;
    private LocalDate validTo;

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

}