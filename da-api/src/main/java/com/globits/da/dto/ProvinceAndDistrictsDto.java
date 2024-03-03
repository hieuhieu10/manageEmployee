package com.globits.da.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProvinceAndDistrictsDto {
    private ProvinceDto provinceDto;
    private List<DistrictDto> districtDtos;

    public ProvinceDto getProvinceDto() {
        return provinceDto;
    }

    public void setProvinceDto(ProvinceDto provinceDto) {
        this.provinceDto = provinceDto;
    }

    public List<DistrictDto> getDistrictDtos() {
        return districtDtos;
    }

    public void setDistrictDtos(List<DistrictDto> districtDtos) {
        this.districtDtos = districtDtos;
    }
}
