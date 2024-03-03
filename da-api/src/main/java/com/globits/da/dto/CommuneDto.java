package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommuneDto extends BaseObjectDto {
    private String name;
    private UUID districtId;

    public CommuneDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getDistrictId() {
        return districtId;
    }

    public void setDistrictId(UUID districtId) {
        this.districtId = districtId;
    }
}
