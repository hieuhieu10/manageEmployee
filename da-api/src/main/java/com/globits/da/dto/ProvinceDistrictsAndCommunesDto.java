package com.globits.da.dto;

import java.util.List;

public class ProvinceDistrictsAndCommunesDto {
    private ProvinceDto provinceDto;
    private List<DistrictDto> districtDtos;
    private List<CommuneDto> communeDtos;

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

    public List<CommuneDto> getCommuneDtos() {
        return communeDtos;
    }

    public void setCommuneDtos(List<CommuneDto> communeDtos) {
        this.communeDtos = communeDtos;
    }
}
