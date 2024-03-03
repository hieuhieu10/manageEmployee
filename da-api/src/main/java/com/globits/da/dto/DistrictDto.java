package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Commune;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DistrictDto extends BaseObjectDto {

    private String name;
    private UUID provinceId;
    private List<Commune> communes;

    public DistrictDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(UUID provinceId) {
        this.provinceId = provinceId;
    }

    public List<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(List<Commune> communes) {
        this.communes = communes;
    }
}
