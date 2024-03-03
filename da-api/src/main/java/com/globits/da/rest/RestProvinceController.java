package com.globits.da.rest;

import com.globits.da.domain.Province;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceAndDistrictsDto;
import com.globits.da.dto.ProvinceDistrictsAndCommunesDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.service.impl.ProvinceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/province")
public class RestProvinceController {
    @Autowired
    ProvinceServiceImpl provinceServiceImpl;

    @RequestMapping(value = "/showAllProvince", method = RequestMethod.GET)
    public ResponseEntity<List<Province>> getAllProvince(){
        List<Province>  result = provinceServiceImpl.getProvincesWithoutDistricts();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/saveProvince", method = RequestMethod.POST)
    public ResponseEntity<ProvinceDto> saveProvinceToData(@RequestBody ProvinceDto provinceDto){
        ProvinceDto result = provinceServiceImpl.saveProvince(provinceDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteProvinceById", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProvinceById(@RequestParam ("id") UUID id){
        provinceServiceImpl.deleteProvince(id);
        return ResponseEntity.ok("Product with ID " + id + " has been deleted");
    }
    @RequestMapping(value = "/addProvinceAndDistricts", method = RequestMethod.POST)
    public ResponseEntity<ProvinceDto> addProvinceAndDistricts(@RequestBody ProvinceAndDistrictsDto provinceAndDistrictsDto){
        ProvinceDto result = provinceServiceImpl.saveProvinceAndDistricts(provinceAndDistrictsDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/saveProvinceDistrictsAndCommunes", method = RequestMethod.POST)
    public ResponseEntity<ProvinceDto> saveProvinceDistrictsAndCommunes(@RequestBody ProvinceDistrictsAndCommunesDto provinceDistrictsAndCommunesDto){
        ProvinceDto result = provinceServiceImpl.saveProvinceDistrictsAndCommunes(provinceDistrictsAndCommunesDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
