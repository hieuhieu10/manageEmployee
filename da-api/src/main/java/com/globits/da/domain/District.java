package com.globits.da.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globits.core.domain.BaseObject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_district")
public class District extends BaseObject {
    private static final long serialVersionUID = 1L;

    private String name;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Commune> communes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public List<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(List<Commune> communes) {
        this.communes = communes;
    }
}
