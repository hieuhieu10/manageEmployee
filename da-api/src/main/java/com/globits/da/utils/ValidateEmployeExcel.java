package com.globits.da.utils;


import com.globits.da.dto.EmployeeDto;
import org.apache.poi.ss.usermodel.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ValidateEmployeExcel {

    public static EmployeeDto readEmployeeFromRow(Row row) {
        EmployeeDto employeeDto = new EmployeeDto();

        Cell cell;

        cell = row.getCell(0);
        employeeDto.setId(UUID.fromString((cell.getStringCellValue())));

        cell = row.getCell(1);
        employeeDto.setCode(cell.getStringCellValue());

        // Đọc và đặt giá trị cho name
        cell = row.getCell(2);
        employeeDto.setName(cell.getStringCellValue());

        // Đọc và đặt giá trị cho email
        cell = row.getCell(3);
        employeeDto.setEmail(cell.getStringCellValue());

        // Đọc và đặt giá trị cho phoneNumber
        cell = row.getCell(4);
        String phoneNumber;
        // Nếu giá trị trong ô là số, chuyển đổi sang chuỗi
        phoneNumber = String.valueOf((long) cell.getNumericCellValue());
        employeeDto.setPhoneNumber(phoneNumber);

        cell = row.getCell(5);
        employeeDto.setCreatedBy(cell.getStringCellValue());
        // Đọc và đặt giá trị cho provinceId
        cell = row.getCell(6);
        employeeDto.setProvinceId(UUID.fromString(cell.getStringCellValue()));

        // Đọc và đặt giá trị cho districtId
        cell = row.getCell(7);
        employeeDto.setDistrictId(UUID.fromString(cell.getStringCellValue()));

        // Đọc và đặt giá trị cho communeId
        cell = row.getCell(8);
        employeeDto.setCommuneId(UUID.fromString(cell.getStringCellValue()));


        return employeeDto;
    }

//    public static List<EmployeeDto> readEmployeesFromExcel(InputStream inputStream) throws IOException {
//        List<EmployeeDto> employeeDtos = new ArrayList<>();
//
//        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
//            Sheet sheet = workbook.getSheetAt(0); // Đọc sheet đầu tiên
//
//            Iterator<Row> rowIterator = sheet.iterator();
//            rowIterator.next(); // Bỏ qua hàng tiêu đề nếu có
//
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//                EmployeeDto employeeDto = readEmployeeFromRow(row);
//                employeeDtos.add(employeeDto);
//            }
//        }
//        return employeeDtos;
//    }

    public static List<String> validateEmployees(List<EmployeeDto> employeeDtos) {
        List<String> errors = new ArrayList<>();

        for (EmployeeDto employeeDto : employeeDtos) {
            // Validate code
            if (employeeDto.getId() == null) {
                errors.add("ID is required.");
            } else {
                try {
                    UUID.fromString(employeeDto.getId().toString());
                } catch (IllegalArgumentException e) {
                    errors.add("ID must be in UUID format.");
                }
            }
            // Validate code
            if (employeeDto.getCode() == null || employeeDto.getCode().isEmpty()) {
                errors.add("Code is required for employee with name: " + employeeDto.getName());
            } else if (!employeeDto.getCode().matches("\\S{6,10}")) {
                errors.add("Code must be 6-10 characters without space for employee with name: " + employeeDto.getName());
            }

            // Validate name
            if (employeeDto.getName() == null || employeeDto.getName().isEmpty()) {
                errors.add("Name is required for employee with code: " + employeeDto.getCode());
            }

            // Validate email
            if (employeeDto.getEmail() == null || employeeDto.getEmail().isEmpty()) {
                errors.add("Email is required for employee with code: " + employeeDto.getCode());
            } else if (!employeeDto.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                errors.add("Invalid email format for employee with code: " + employeeDto.getCode());
            }

            // Validate phoneNumber
            if (employeeDto.getPhoneNumber() == null || employeeDto.getPhoneNumber().isEmpty()) {
                errors.add("Phone number is required for employee with code: " + employeeDto.getCode());
            } else if (!employeeDto.getPhoneNumber().matches("\\d{1,11}")) {
                errors.add("Phone number must contain only digits with maximum length of 11 for employee with code: " + employeeDto.getCode());
            }

            // Validate age
            if (employeeDto.getAge() < 0) {
                errors.add("Age cannot be negative for employee with code: " + employeeDto.getCode());
            }
        }

        return errors;
    }
}
