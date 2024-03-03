package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.Converter.CommuneConverter;
import com.globits.da.Converter.DistrictConverter;
import com.globits.da.Converter.ProvinceConverter;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.*;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProvinceServiceImpl extends GenericServiceImpl<Province, UUID> implements ProvinceService {
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    ProvinceConverter provinceConverter;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    DistrictConverter districtConverter;
    @Autowired
    CommuneRepository communeRepository;
    @Autowired
    CommuneConverter communeConverter;
    @Override
    public List<Province> getProvincesWithoutDistricts() {
        return provinceRepository.findAll();
    }
    @Override
    //add or update province
    public ProvinceDto saveProvince(ProvinceDto provinceDto) {
        Province province = new Province();
        if (provinceDto.getId() != null) {
            province = provinceRepository.findById(provinceDto.getId()).orElse(null);
            if (province != null) {
                province = provinceConverter.toProvince(provinceDto);
            } else {
                province = provinceConverter.toProvince(provinceDto);
            }
        }
        province.setCreatedBy(provinceDto.getCreatedBy());
        provinceRepository.save(province);
        return provinceConverter.toDto(province);
    }
    @Override
    public void deleteProvince(UUID id) {
        provinceRepository.deleteById(id);
    }

    @Override
    public ProvinceDto saveProvinceAndDistricts(ProvinceAndDistrictsDto provinceAndDistrictsDto) {
        ProvinceDto provinceDto = provinceAndDistrictsDto.getProvinceDto();
        List<DistrictDto> districtDtos = provinceAndDistrictsDto.getDistrictDtos();

        Province province = new Province();
        if (provinceDto.getId() != null){
            province = provinceRepository.findById(provinceDto.getId()).orElse(null);
            if (province !=null){
                province = provinceConverter.toProvince(provinceDto);
            }else {
                province = provinceConverter.toProvince(provinceDto);
            }
            province.setCreatedBy(provinceDto.getCreatedBy());
        }
        List<District> districts = new ArrayList<>();
        District district = new District();
        for (DistrictDto districtDto: districtDtos) {
            if (districtDto.getId() != null) {
                district = districtRepository.findById(districtDto.getId()).orElse(null);
                if (district != null) {
                    district = districtConverter.toDistrict(districtDto);
                    districts.add(district);
                } else {
                    district = districtConverter.toDistrict(districtDto);
                    districts.add(district);
                }
            }
            district.setCreatedBy(districtDto.getCreatedBy());
            district.setProvince(province);
        }
        // Save or update districts in the province object
        province.setDistricts(districts);

        // Save or update province and district
        province = provinceRepository.save(province);
        district = districtRepository.save(district);
        return provinceConverter.toDto(province,districts);
    }

    @Override
    public ProvinceDto saveProvinceDistrictsAndCommunes(ProvinceDistrictsAndCommunesDto provinceDistrictsAndCommunesDto) {
        ProvinceDto provinceDto = provinceDistrictsAndCommunesDto.getProvinceDto();
        List<DistrictDto> districtDtos = provinceDistrictsAndCommunesDto.getDistrictDtos();
        List<CommuneDto> communeDtos = provinceDistrictsAndCommunesDto.getCommuneDtos();

        Province province = new Province();
        if (provinceDto.getId() != null){
            province = provinceRepository.findById(provinceDto.getId()).orElse(null);
            if (province !=null){
                province = provinceConverter.toProvince(provinceDto);
            }else {
                province = provinceConverter.toProvince(provinceDto);
            }
            province.setCreatedBy(provinceDto.getCreatedBy());
        }

        List<District> districts = new ArrayList<>();
        District district = new District();
        for (DistrictDto districtDto: districtDtos) {
            if (districtDto.getId() != null) {
                district = districtRepository.findById(districtDto.getId()).orElse(null);
                if (district != null) {
                    district = districtConverter.toDistrict(districtDto);
                    districts.add(district);
                } else {
                    district = districtConverter.toDistrict(districtDto);
                    districts.add(district);
                }
            }
            district.setCreatedBy(districtDto.getCreatedBy());
            district.setProvince(province);
        }

        List<Commune> communes = new ArrayList<>();
        Commune commune = new Commune();
        for (CommuneDto communeDto: communeDtos) {
            if (communeDto.getId() != null) {
                commune = communeRepository.findById(communeDto.getId()).orElse(null);
                if (commune != null) {
                    commune = communeConverter.toCommune(communeDto);
                    communes.add(commune);
                } else {
                    commune = communeConverter.toCommune(communeDto);
                    communes.add(commune);
                }
            }
            commune.setCreatedBy(communeDto.getCreatedBy());
            commune.setDistrict(district);
        }
        // Save or update districts in the province object
        province.setDistricts(districts);
        district.setCommunes(communes);

        // Save or update province and district
        province = provinceRepository.save(province);
        district = districtRepository.save(district);
        commune = communeRepository.save(commune);
        return provinceConverter.toDto(province,districts,communes);
    }
}
