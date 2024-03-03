package com.globits.da.domain;

import com.globits.core.domain.BaseObject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_certificate")
public class Certificate extends BaseObject {

    private String name;

    @OneToMany(mappedBy = "certificate")
    private List<EmployeeCertificate> employeeCertificates;

    @ManyToOne
    @JoinColumn(name = "province_id")//, referencedColumnName = "id"
    private Province province;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeCertificate> getEmployeeCertificates() {
        return employeeCertificates;
    }

    public void setEmployeeCertificates(List<EmployeeCertificate> employeeCertificates) {
        this.employeeCertificates = employeeCertificates;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
