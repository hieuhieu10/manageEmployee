package com.globits.da.rest;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.search.EmployeeSearchDto;
import com.globits.da.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/api/employee")
public class RestEmployeeController {
    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @RequestMapping(value = "/showAllEmployee", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> result = employeeServiceImpl.getAllEmployees();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/showAllEmployeeBySearch", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDto>> getAllEmployeesBySearch(@RequestBody EmployeeSearchDto employeeSearchDto){
        List<EmployeeDto> result = employeeServiceImpl.getEmployeesBySearch(employeeSearchDto);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteEmployeeBy/{id}", method = RequestMethod.DELETE)
    public void deleteEmployeesBySearch(@PathVariable UUID id){
        employeeServiceImpl.deleteEmployeesById(id);
    }
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportToExcel() {
        List<Employee> employees = employeeServiceImpl.getAllEmployees();
        ByteArrayOutputStream stream = employeeServiceImpl.exportToExcel(employees);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "employees.xlsx");

        return new ResponseEntity<>(stream.toByteArray(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
    public ResponseEntity<EmployeeDto> saveEmployee(@Validated @RequestBody EmployeeDto employeeDto){
        EmployeeDto result = employeeServiceImpl.saveEmployee(employeeDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
