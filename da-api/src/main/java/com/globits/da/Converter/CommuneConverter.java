package com.globits.da.Converter;

import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;
import org.springframework.stereotype.Component;

@Component
public class CommuneConverter {
    public CommuneDto toCommuneDto(Commune commune, District district) {
        CommuneDto communeDto = new CommuneDto();
        communeDto.setId(commune.getId());
        communeDto.setName(commune.getName());
        if (commune.getDistrict() != null) {
            communeDto.setDistrictId(district.getId());
        }
        return communeDto;
    }
    public Commune toCommune(CommuneDto communeDto) {
        Commune commune = new Commune();
        commune.setId(communeDto.getId());
        commune.setName(communeDto.getName());
        return commune;
    }
    public CommuneDto toCommuneDto(Commune commune) {
        CommuneDto communeDto = new CommuneDto();
        communeDto.setId(commune.getId());
        communeDto.setName(commune.getName());
        return communeDto;
    }
}
