package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Commune;
import com.globits.da.dto.CommuneDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public interface CommuneService {
    Map<String, Object> getAllCommune();
    CommuneDto saveCommune(CommuneDto communeDto);
    List<CommuneDto> searchAllCommuneByDistrictId(UUID districtId);
    void deleteCommuneById(UUID id);
}
