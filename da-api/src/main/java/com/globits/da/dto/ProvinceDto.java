package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProvinceDto extends BaseObjectDto {
    private String name;
    private List<District> districts;
    private List<Commune> communes;

    public ProvinceDto() {
    }

    public ProvinceDto(String name) {
        this.name = name;
    }

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

    public List<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(List<Commune> communes) {
        this.communes = communes;
    }
}
