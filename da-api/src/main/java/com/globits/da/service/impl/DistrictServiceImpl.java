package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.Converter.CommuneConverter;
import com.globits.da.Converter.DistrictConverter;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictAndCommunesDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DistrictServiceImpl extends GenericServiceImpl<District, UUID> implements DistrictService  {

    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    DistrictConverter districtConverter;
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    ProvinceServiceImpl provinceServiceImpl;
    @Autowired
    CommuneRepository communeRepository;
    @Autowired
    CommuneConverter communeConverter;
    @Override
    public List<District> getDistrictsWithoutCommune() {
        return districtRepository.findAll();
    }
    @Override
    public Map<String, Object> getAllDistrict() {
        List<District> districts = new ArrayList<>();
        Province province = new Province();
        List<DistrictDto> districtDtos = new ArrayList<>();
        districts = districtRepository.findAll();
        for (District district : districts) {
            province = district.getProvince();
            districtDtos.add(districtConverter.toDistrictDto(district, province));
        }
        Map<String, Object> response = new HashMap<>();
        response.put("districts", districtDtos);
        return response;
    }
    @Override
    public DistrictDto saveDistrict(DistrictDto districtDto) {
        District district = new District();
        if (districtDto.getId() != null){
            district = districtRepository.findById(districtDto.getId()).orElse(null);
            if (district != null){
                district = districtConverter.toDistrict(districtDto);
            }else {
                district = districtConverter.toDistrict(districtDto);
            }
        }
        boolean provinceFound = false; // Biến đánh dấu liệu có tồn tại Province hay không

        for (Province province : provinceServiceImpl.getProvincesWithoutDistricts()) {
            if (province.getId().equals(districtDto.getProvinceId())) {
                // Nếu tìm thấy Province, thực hiện xử lý tương ứng
                province = provinceRepository.findById(districtDto.getProvinceId()).orElse(null);
                district.setProvince(province);
                district.setCreatedBy(districtDto.getCreatedBy());
                districtRepository.save(district);
                districtDto = districtConverter.toDistrictDto(district, province);
                provinceFound = true; // Đánh dấu rằng đã tìm thấy Province
                break; // Thoát vòng lặp sau khi tìm thấy Province
            }
        }
        if (!provinceFound) {
            // Nếu không tìm thấy Province, xử lý tương ứng
            throw new RuntimeException("Province not found in the database: " + districtDto.getProvinceId());
        }
        return districtDto;
    }

    @Override
    public List<DistrictDto> searchAllDistrictByProvinceId(UUID provinceId) {
        List<DistrictDto> districtDtos = new ArrayList<>();
        List<District> districts = new ArrayList<>();
        districts = districtRepository.findAllByProvinceId(provinceId);
        for (District district: districts){
            districtDtos.add(districtConverter.toDistrictDto(district));
        }
        return districtDtos;
    }

    @Override
    public void deleteDistrictById(UUID id) {
        districtRepository.deleteById(id);
    }

    @Override
    public DistrictDto saveDistrictAndCommune(DistrictAndCommunesDto districtAndCommunesDto) {
        DistrictDto districtDto = districtAndCommunesDto.getDistrictDto();
        List<CommuneDto> communeDtos = districtAndCommunesDto.getCommuneDtos();

        District district = new District();
        if (districtDto.getId() != null){
            district = districtRepository.findById(districtDto.getId()).orElse(null);
            if (district !=null){
                district = districtConverter.toDistrict(districtDto);
            }else {
                district = districtConverter.toDistrict(districtDto);
            }
            district.setCreatedBy(districtDto.getCreatedBy());
        }
        boolean provinceFound = false; // Biến đánh dấu liệu có tồn tại Province hay không
        for (Province province : provinceServiceImpl.getProvincesWithoutDistricts()) {
            if (province.getId().equals(districtDto.getProvinceId())) {
                // Nếu tìm thấy Province, thực hiện xử lý tương ứng
                province = provinceRepository.findById(districtDto.getProvinceId()).orElse(null);
                district.setProvince(province);
                district.setCreatedBy(districtDto.getCreatedBy());
                provinceFound = true; // Đánh dấu rằng đã tìm thấy Province
                break; // Thoát vòng lặp sau khi tìm thấy Province
            }
        }
        if (!provinceFound) {
            // Nếu không tìm thấy Province, xử lý tương ứng
            throw new RuntimeException("Province not found in the database: " + districtDto.getProvinceId());
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
        district.setCommunes(communes);

        // Save or update province and district
        district = districtRepository.save(district);
        commune = communeRepository.save(commune);
        return districtConverter.toDistrictDto(district,communes);
    }
}
