package com.globits.da.repository;

import com.globits.da.domain.Employee;
import com.globits.da.dto.search.EmployeeSearchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query("select e from Employee e where " +
            "(:#{#searchDTO.name} IS NULL OR e.name = :#{#searchDTO.name}) AND " +
            "(:#{#searchDTO.email} IS NULL OR e.email = :#{#searchDTO.email}) AND " +
            "(:#{#searchDTO.age} IS NULL OR e.age = :#{#searchDTO.age})AND " +
            "(:#{#searchDTO.phoneNumber} IS NULL OR e.phoneNumber = :#{#searchDTO.phoneNumber})"
    )
    List<Employee> findBySearchCriteria(@Param("searchDTO") EmployeeSearchDto searchDTO);
}
