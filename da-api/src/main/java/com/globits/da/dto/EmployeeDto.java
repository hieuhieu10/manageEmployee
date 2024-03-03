package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.EmployeeCertificate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;
import java.util.List;

@Component
public class EmployeeDto extends BaseObjectDto{
    @NotBlank(message = "Code is required")
    @Pattern(regexp = "\\S{6,10}", message = "Code must be 6-10 characters without space")
    private String code;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{1,11}", message = "Phone number must contain only digits with maximum length of 11")
    private String phoneNumber;

    @Min(value = 0, message = "Age cannot be negative")
    private int age;

    private UUID provinceId;
    private UUID districtId;
    private UUID communeId;

    private List<EmployeeCertificate> employeeCertificates;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UUID getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(UUID provinceId) {
        this.provinceId = provinceId;
    }

    public UUID getDistrictId() {
        return districtId;
    }

    public void setDistrictId(UUID districtId) {
        this.districtId = districtId;
    }

    public UUID getCommuneId() {
        return communeId;
    }

    public void setCommuneId(UUID communeId) {
        this.communeId = communeId;
    }

    public List<EmployeeCertificate> getEmployeeCertificates() {
        return employeeCertificates;
    }

    public void setEmployeeCertificates(List<EmployeeCertificate> employeeCertificates) {
        this.employeeCertificates = employeeCertificates;
    }
}
