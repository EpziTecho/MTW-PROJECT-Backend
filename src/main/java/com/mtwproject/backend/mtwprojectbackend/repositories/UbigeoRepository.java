package com.mtwproject.backend.mtwprojectbackend.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Ubigeo;

public interface UbigeoRepository  extends CrudRepository <Ubigeo, Long>{
    
    List<Ubigeo> findByDepartment(String department);
    List<Ubigeo> findByProvince(String province);
    List<Ubigeo> findByDistrict(String district); 
    List<Ubigeo> findByDepartmentAndProvince(String department, String province);
    List<Ubigeo> findByDepartmentAndProvinceAndDistrict(String department, String province, String district);

}
