package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CertificateDto extends BaseObjectDto {
    private String name;
    private UUID provinceId;
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
}
