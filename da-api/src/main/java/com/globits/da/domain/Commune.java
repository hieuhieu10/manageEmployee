package com.globits.da.domain;

import com.globits.core.domain.BaseObject;

import javax.persistence.*;

@Entity
@Table(name = "tbl_commune")
public class Commune extends BaseObject {

    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
