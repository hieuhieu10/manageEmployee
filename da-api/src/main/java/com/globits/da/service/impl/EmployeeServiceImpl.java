package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.Converter.EmployeeConverter;
import com.globits.da.domain.*;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.search.EmployeeSearchDto;
import com.globits.da.repository.*;
import com.globits.da.service.EmployeeService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.globits.da.utils.ValidateEmployeExcel.*;

@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employee, UUID> implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeConverter employeeConverter;
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    CommuneRepository communeRepository;
    @Autowired
    CertificateRepository certificateRepository;
    @Autowired
    EmployeeCertificateRepository employeeCertificateRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<EmployeeDto> getEmployeesBySearch(EmployeeSearchDto employeeSearchDto) {
        List<Employee> employeeEntities = employeeRepository.findBySearchCriteria(employeeSearchDto);
        return employeeEntities.stream().map(employeeConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteEmployeesById(UUID id) {
        employeeRepository.deleteById(id);
    }

    public ByteArrayOutputStream exportToExcel(List<Employee> employees) {
        try (Workbook workbook = new XSSFWorkbook()) {//create workbook format .xlxs
            Sheet sheet = workbook.createSheet("Employees"); //Create a sheet named "Employees"

            // create title row
            Row headerRow = sheet.createRow(0);
            String[] columns = {"id", "name", "code", "Email", "Phone", "Age"};

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // add data
            for (int rowIndex = 0; rowIndex < employees.size(); rowIndex++) {
                Row row = sheet.createRow(rowIndex + 1);
                Employee employee = employees.get(rowIndex);

                row.createCell(0).setCellValue(rowIndex + 1);
                row.createCell(1).setCellValue(employee.getName());
                row.createCell(2).setCellValue(employee.getCode());
                row.createCell(3).setCellValue(employee.getEmail());
                row.createCell(4).setCellValue(employee.getPhoneNumber());
                row.createCell(5).setCellValue(employee.getAge());
            }

            // create stream to export file Excel
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            workbook.write(stream);
            return stream;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<EmployeeDto> saveEmployees(List<EmployeeDto> employeeDtos) {
        Employee employee = new Employee();
        EmployeeCertificate employeeCertificate = new EmployeeCertificate();
        for (EmployeeDto employeeDto : employeeDtos) {
            Province province = provinceRepository.findById(employeeDto.getProvinceId()).orElse(null);
            District district = districtRepository.findByIdAndProvinceId(employeeDto.getDistrictId(), employeeDto.getProvinceId());
            Commune commune = communeRepository.findByIdAndDistrictId(employeeDto.getCommuneId(), employeeDto.getDistrictId());

            for (EmployeeCertificate employeeCertificateDto : employeeDto.getEmployeeCertificates()) {
                Certificate certificate = certificateRepository.findById(employeeCertificateDto.getCertificate().getId()).orElse(null);
                List<EmployeeCertificate> employeeCertificates = employeeCertificateRepository.findAllByEmployeeIdAndCertificateId(
                        employeeDto.getId(), employeeCertificateDto.getCertificate().getId());


                if (employeeCertificates != null) {
                    Optional<Certificate> certificate1 = certificateRepository.findByIdAndProvinceId(
                            certificate.getId(), certificate.getProvince().getId());
                    for (EmployeeCertificate employeeCertificate1 : employeeCertificates) {
                        LocalDate validTo = employeeCertificate1.getValidTo();
                        LocalDate validFrom = employeeCertificate1.getValidFrom();
                        if ((certificate1.isPresent() && (employeeCertificateDto.getValidFrom().isAfter(validFrom) && employeeCertificateDto.getValidTo().isBefore(validTo)))
                                || (employeeCertificates.size() >= 3 && (employeeCertificateDto.getValidFrom().isAfter(validFrom) && employeeCertificateDto.getValidTo().isBefore(validTo)))
                        ) {
                            throw new RuntimeException("Employee could this certificate by 1 province and <3 certificate : " + employeeDto.getId() + "\n" +
                                    employeeCertificateDto.getCertificate().getId() + "\n" +
                                    employeeDto.getProvinceId());
                        }
                    }

                }
                if (certificate != null) {
                    employeeCertificate.setCertificate(certificate);
                    employeeCertificate.setValidTo(employeeCertificateDto.getValidTo());
                    employeeCertificate.setValidFrom(employeeCertificateDto.getValidFrom());
                }
            }

            if (employeeDto.getId() != null && employeeDto.getProvinceId() != null &&
                    employeeDto.getDistrictId() != null && employeeDto.getCommuneId() != null) {

                employee = employeeRepository.findById(employeeDto.getId()).orElse(null);
                if (employee != null) {
                    employee = employeeConverter.toEmployee(employeeDto);

                } else {
                    if (district != null && commune != null) {
                        employee = employeeConverter.toEmployee(employeeDto);
                    } else {
                        return null;
                    }
                }
                employee.setProvince(province);
                employee.setDistrict(district);
                employee.setCommune(commune);
                employeeCertificate.setEmployee(employee);
            }

            employee = employeeRepository.save(employee);
            employeeDto = employeeConverter.toDto(employee, employee.getEmployeeCertificates());
            employeeCertificate = employeeCertificateRepository.save(employeeCertificate);
        }
        return employeeDtos;
    }

    public void importEmployees(InputStream excelFile) {
        try {
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0); // Assume the data is in the first sheet

            List<EmployeeDto> employeeDtos = new ArrayList<>();

            List<String> errorMessages = new ArrayList<>(); // Danh sách lưu trữ thông điệp lỗi
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                EmployeeDto employeeDto = readEmployeeFromRow(row);
                employeeDtos.add(employeeDto);
            }
            // Iterate over rows (skip header row if any)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                List<String> validationErrors = validateEmployees(employeeDtos);

                if (!validationErrors.isEmpty()) {
                    for (String errorMessage : validationErrors) {
                        errorMessages.add("Row " + (i + 1) + ": " + errorMessage); // Lưu thông điệp lỗi và số dòng tương ứng
                    }
                } else {
                    // Save employees to the database if there are no validation errors
                    saveEmployee(employeeDtos);
                }
            }

            if (!errorMessages.isEmpty()) {
                // In ra thông điệp lỗi cho từng dòng và dừng quá trình nhập liệu
                for (String errorMessage : errorMessages) {
                    System.out.println(errorMessage);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<EmployeeDto> saveEmployee(List<EmployeeDto> employeeDtos) {
        Employee employee = new Employee();
        for (EmployeeDto employeeDto : employeeDtos) {
            Province province = provinceRepository.findById(employeeDto.getProvinceId()).orElse(null);
            District district = districtRepository.findByIdAndProvinceId(employeeDto.getDistrictId(), employeeDto.getProvinceId());
            Commune commune = communeRepository.findByIdAndDistrictId(employeeDto.getCommuneId(), employeeDto.getDistrictId());

            if (employeeDto.getId() != null && employeeDto.getProvinceId() != null &&
                    employeeDto.getDistrictId() != null && employeeDto.getCommuneId() != null) {

                employee = employeeRepository.findById(employeeDto.getId()).orElse(null);
                if (employee != null) {
                    employee = employeeConverter.toEmployee(employeeDto);

                } else {
                    if (district != null && commune != null) {
                        employee = employeeConverter.toEmployee(employeeDto);
                    } else {
                        return null;
                    }
                }
                employee.setProvince(province);
                employee.setDistrict(district);
                employee.setCommune(commune);

            }
            employee = employeeRepository.save(employee);
            employeeDto = employeeConverter.toDto(employee, employee.getEmployeeCertificates());
        }
        return employeeDtos;
    }
}