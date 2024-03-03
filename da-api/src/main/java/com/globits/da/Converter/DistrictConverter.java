package com.globits.da.Converter;

import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DistrictConverter {
    public DistrictDto toDistrictDto(District district, Province province) {
        DistrictDto districtDto = new DistrictDto();
        districtDto.setId(district.getId());
        districtDto.setName(district.getName());
        if (district.getProvince() != null) {
            districtDto.setProvinceId(province.getId());
        }
        return districtDto;
    }

    public District toDistrict(DistrictDto districtDto) {
        District district = new District();
        district.setId(districtDto.getId());
        district.setName(districtDto.getName());
        return district;
    }
    public DistrictDto toDistrictDto(District district) {
        DistrictDto districtDto = new DistrictDto();
        districtDto.setId(district.getId());
        districtDto.setName(district.getName());
        return districtDto;
    }
    public DistrictDto toDistrictDto(District district, List<Commune> communes) {
        DistrictDto districtDto = new DistrictDto();
        districtDto.setId(district.getId());
        districtDto.setName(district.getName());
        districtDto.setCommunes(communes);
        return districtDto;
    }
}
