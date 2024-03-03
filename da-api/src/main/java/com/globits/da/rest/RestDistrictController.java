package com.globits.da.rest;

import com.globits.da.dto.DistrictAndCommunesDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceAndDistrictsDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.service.impl.DistrictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/district")
public class RestDistrictController {
    @Autowired
    DistrictServiceImpl districtServiceImpl;

    @RequestMapping(value = "/showAllDistrict", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getAllProvince(){
        Map<String,Object>  result = districtServiceImpl.getAllDistrict();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/saveDistrictFolProv", method = RequestMethod.POST)
    public ResponseEntity<DistrictDto> saveDistrictFolProv(@RequestBody DistrictDto districtDto){
        DistrictDto result = districtServiceImpl.saveDistrict(districtDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteDistrictById", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteDistrictById(@RequestParam ("id") UUID id){
        districtServiceImpl.deleteDistrictById(id);
        return ResponseEntity.ok("District with ID " + id + " has been deleted");
    }
    @RequestMapping(value = "/SearchAllDistrictByProvinceId", method = RequestMethod.GET)
    public ResponseEntity<List<DistrictDto>> SearchAllDistrict(@RequestParam("id") UUID id){
        List<DistrictDto> result = districtServiceImpl.searchAllDistrictByProvinceId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/saveDistrictAndCommunes", method = RequestMethod.POST)
    public ResponseEntity<DistrictDto> saveDistrictAndCommunes(@RequestBody DistrictAndCommunesDto districtAndCommunesDto){
        DistrictDto result = districtServiceImpl.saveDistrictAndCommune(districtAndCommunesDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
