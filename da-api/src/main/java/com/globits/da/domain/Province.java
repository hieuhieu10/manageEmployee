package com.globits.da.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globits.core.domain.BaseObject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "tbl_province")
public class Province  extends BaseObject {
//    private static final long serialVersionUID = 1L;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "province",cascade = CascadeType.ALL)// orphanRemoval = true
    @JsonIgnore
    private List<District> districts;

    @OneToMany(mappedBy = "province")
    @JsonIgnore
    private List<Certificate> certificates;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }
}
