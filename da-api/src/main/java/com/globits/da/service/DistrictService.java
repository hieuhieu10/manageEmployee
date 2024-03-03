package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.District;
import com.globits.da.dto.DistrictAndCommunesDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceAndDistrictsDto;
import com.globits.da.dto.ProvinceDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public interface DistrictService  {
    List<District> getDistrictsWithoutCommune();
    Map<String, Object> getAllDistrict();
    DistrictDto saveDistrict(DistrictDto districtDto);
    List<DistrictDto> searchAllDistrictByProvinceId(UUID provinceId);
    void deleteDistrictById(UUID id);
    DistrictDto saveDistrictAndCommune(DistrictAndCommunesDto districtAndCommunesDto);
}
