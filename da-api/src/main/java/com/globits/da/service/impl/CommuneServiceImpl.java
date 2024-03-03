package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.Converter.CommuneConverter;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommuneServiceImpl extends GenericServiceImpl<Commune, UUID> implements CommuneService {

    @Autowired
    private CommuneRepository communeRepository;
    @Autowired
    private CommuneConverter communeConverter;
    @Autowired
    private DistrictServiceImpl districtServiceImpl;
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public Map<String, Object> getAllCommune() {
        List<Commune> communes = new ArrayList<>();
        District district = new District();
        List<CommuneDto> communeDtos = new ArrayList<>();
        communes = communeRepository.findAll();
        for (Commune commune : communes) {
            district = commune.getDistrict();
            communeDtos.add(communeConverter.toCommuneDto(commune, district));
        }
        Map<String, Object> response = new HashMap<>();
        response.put("communes", communeDtos);

        return response;
    }

    @Override
    public CommuneDto saveCommune(CommuneDto communeDto) {
        Commune commune = new Commune();
        if (communeDto.getId() != null){
            commune = communeRepository.findById(communeDto.getId()).orElse(null);
            if (commune != null){
                commune = communeConverter.toCommune(communeDto);
            }else {
                commune = communeConverter.toCommune(communeDto);
            }
        }
        boolean districtFound = false; // Biến đánh dấu liệu có tồn tại district hay không
        for (District district : districtServiceImpl.getDistrictsWithoutCommune()) {
            if (district.getId().equals(communeDto.getDistrictId())) {
                // Nếu tìm thấy district, thực hiện xử lý tương ứng
                district = districtRepository.findById(communeDto.getDistrictId()).orElse(null);

                commune.setDistrict(district);
                commune.setCreatedBy(communeDto.getCreatedBy());
                communeRepository.save(commune);
                communeDto = communeConverter.toCommuneDto(commune, district);
                districtFound = true; // Đánh dấu rằng đã tìm thấy district
                break; // Thoát vòng lặp sau khi tìm thấy district
            }
        }

        if (!districtFound) {
            // Nếu không tìm thấy district, xử lý tương ứng
            throw new RuntimeException("Province not found in the database: " + communeDto.getDistrictId());
        }
        return communeDto;
    }

    @Override
    public List<CommuneDto> searchAllCommuneByDistrictId(UUID districtId) {
        List<CommuneDto> communeDtos = new ArrayList<>();
        List<Commune> communes = new ArrayList<>();
        communes = communeRepository.findAllByDistrictId(districtId);
        for (Commune commune: communes){
            communeDtos.add(communeConverter.toCommuneDto(commune));
        }
        return communeDtos;
    }

    @Override
    public void deleteCommuneById(UUID id) {
        communeRepository.deleteById(id);
    }
}
