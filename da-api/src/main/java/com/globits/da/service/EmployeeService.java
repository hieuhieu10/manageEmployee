package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.search.EmployeeSearchDto;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public interface EmployeeService  {
    List<Employee> getAllEmployees();
    List<EmployeeDto> getEmployeesBySearch(EmployeeSearchDto employeeSearchDto);
    void deleteEmployeesById(UUID id);
    ByteArrayOutputStream exportToExcel(List<Employee> employees);
    List<EmployeeDto> saveEmployees(List<EmployeeDto> employeeDtos);
    void importEmployees(InputStream excelFile);
}
