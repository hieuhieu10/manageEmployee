package com.globits.da.rest;

import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.service.impl.CommuneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/commune")
public class RestCommuneController {

    @Autowired
    private CommuneServiceImpl communeServiceImpl;

    @RequestMapping(value = "/showAllCommune", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getAllCommune(){
        Map<String,Object>  result = communeServiceImpl.getAllCommune();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/saveCommuneFolDistr", method = RequestMethod.POST)
    public ResponseEntity<CommuneDto> saveDistrictFolProv(@RequestBody CommuneDto communeDto){
        CommuneDto result = communeServiceImpl.saveCommune(communeDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteCommuneById", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCommuneById(@RequestParam("id") UUID id){
        communeServiceImpl.deleteCommuneById(id);
        return ResponseEntity.ok("Commune with ID " + id + " has been deleted");
    }
    @RequestMapping(value = "/SearchAllCommuneByProvinceId", method = RequestMethod.GET)
    public ResponseEntity<List<CommuneDto>> SearchAllCommune(@RequestParam("id") UUID id){
        List<CommuneDto> result = communeServiceImpl.searchAllCommuneByDistrictId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
