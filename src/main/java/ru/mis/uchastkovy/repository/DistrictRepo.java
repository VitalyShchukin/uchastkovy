package ru.mis.uchastkovy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.mis.uchastkovy.model.sqlModel.DistrictDto;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "districts", path = "districts")
public interface DistrictRepo extends CrudRepository<DistrictDto, Long> {

    @Query(value = "select " +
            " mcd.id as id " +
            " , mcd.name as distr " +
            " , mcdt.name as separation " +
            " , mcs.name as distr_type" +
            " from public.md_clinic_district mcd " +
            " join public.md_clinic_district_type mcdt on mcdt.id = mcd.type_id " +
            " join public.md_clinic_separation mcs on mcs.id = mcd.separation_id " +
            " order by 1 asc ",
            countQuery = "select count(*) " +
                    "from public.md_clinic_district mcd " +
                    "join public.md_clinic_district_type mcdt on mcdt.id = mcd.type_id " +
                    "join public.md_clinic_separation mcs on mcs.id = mcd.separation_id " +
                    " order by 1 asc ",
            nativeQuery = true)
    @RestResource
    Page<DistrictDto> findAllDistricts(Pageable pageable);
}

