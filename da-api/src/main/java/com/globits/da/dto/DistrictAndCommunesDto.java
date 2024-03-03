package com.globits.da.dto;

import java.util.List;

public class DistrictAndCommunesDto {
    private DistrictDto districtDto;
    private List<CommuneDto> communeDtos;

    public DistrictDto getDistrictDto() {
        return districtDto;
    }

    public void setDistrictDto(DistrictDto districtDto) {
        this.districtDto = districtDto;
    }

    public List<CommuneDto> getCommuneDtos() {
        return communeDtos;
    }

    public void setCommuneDtos(List<CommuneDto> communeDto) {
        this.communeDtos = communeDto;
    }
}
