package com.globits.da.Converter;

import com.globits.da.domain.Employee;
import com.globits.da.domain.EmployeeCertificate;
import com.globits.da.dto.EmployeeDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeConverter {
    public EmployeeDto toDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(employee.getName());
        employeeDto.setAge(employee.getAge());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        return employeeDto;
    }
    public Employee toEmployee(EmployeeDto employeeDto){

        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setCode(employeeDto.getCode());
        employee.setName(employeeDto.getName());
        employee.setAge(employeeDto.getAge());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setEmail(employeeDto.getEmail());
        employee.setCreatedBy(employeeDto.getCreatedBy());

        return employee;
    }
    public EmployeeDto toDto(Employee employee, List<EmployeeCertificate> employeeCertificates){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(employee.getName());
        employeeDto.setAge(employee.getAge());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setEmployeeCertificates(employeeCertificates);
        return employeeDto;
    }

}
