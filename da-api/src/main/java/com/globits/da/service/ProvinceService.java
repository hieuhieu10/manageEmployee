package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Province;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceAndDistrictsDto;
import com.globits.da.dto.ProvinceDistrictsAndCommunesDto;
import com.globits.da.dto.ProvinceDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public interface ProvinceService  {
    List<Province> getProvincesWithoutDistricts();
    ProvinceDto saveProvince(ProvinceDto provinceDto);
    void deleteProvince(UUID id);
    ProvinceDto saveProvinceAndDistricts(ProvinceAndDistrictsDto provinceAndDistrictsDto);
    ProvinceDto saveProvinceDistrictsAndCommunes(ProvinceDistrictsAndCommunesDto provinceDistrictsAndCommunesDto);
}
