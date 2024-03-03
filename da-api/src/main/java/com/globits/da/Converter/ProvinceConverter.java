package com.globits.da.Converter;

import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProvinceConverter {

    public ProvinceDto toDto(Province province) {
        ProvinceDto provinceDto = new ProvinceDto();
        District district = new District();
        provinceDto.setId(province.getId());
        provinceDto.setName(province.getName());
        return provinceDto;
    }
    public Province toProvince(ProvinceDto provinceDto){
        Province province = new Province();
        province.setId(provinceDto.getId());
        province.setName(provinceDto.getName());
        return province;
    }
    public ProvinceDto toDto(Province province, List<District> districts) {
        ProvinceDto provinceDto = new ProvinceDto();
        provinceDto.setId(province.getId());
        provinceDto.setName(province.getName());
        provinceDto.setDistricts(districts);
        return provinceDto;
    }
    public ProvinceDto toDto(Province province, List<District> districts, List<Commune> communes) {
        ProvinceDto provinceDto = new ProvinceDto();
        provinceDto.setId(province.getId());
        provinceDto.setName(province.getName());
        provinceDto.setDistricts(districts);
        provinceDto.setCommunes(communes);
        return provinceDto;
    }
}
