package com.globits.da.repository;

import com.globits.da.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface DistrictRepository extends JpaRepository<District, UUID> {

    List<District> findAllByProvinceId(UUID provinceId);

    District findByIdAndProvinceId(UUID districtId, UUID provinceId);
}
