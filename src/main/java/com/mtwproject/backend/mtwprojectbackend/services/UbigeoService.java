package com.mtwproject.backend.mtwprojectbackend.services;

import java.util.List;
import java.util.Optional;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Ubigeo;

public interface UbigeoService {

    List<Ubigeo> findAll();

    Optional<Ubigeo> findById(Long id);

    Ubigeo save(Ubigeo distrit);

    Optional<Ubigeo> update(Ubigeo distrit, Long id);

    void remove(Long id);

    List<Ubigeo> findByProvince(String province);

    List<Ubigeo> findByDistrict(String district);

    List<Ubigeo> findByDepartment(String department);

    List<Ubigeo> findByDepartmentAndProvince(String department, String province);

    List<Ubigeo> findByDepartmentAndProvinceAndDistrict(String department, String province, String district);

    List<Ubigeo> listUbigeosLimaCallao();
}
