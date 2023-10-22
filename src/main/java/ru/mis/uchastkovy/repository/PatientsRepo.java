package ru.mis.uchastkovy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.mis.uchastkovy.model.PatientDto;

@RepositoryRestResource(collectionResourceRel = "patients", path = "patients")
public interface PatientsRepo extends CrudRepository<PatientDto, Long> {

    @Query(value = "select " +
            " pi2.id as id " +
            " , concat_ws(' ', pi2.surname, pi2.name, pi2.patr_name, to_char(pi2.birth_dt, '(dd.mm.yyyy)')) as fio " +
            " , mcd.name as distr " +
            " , mcd.id as distr_id " +
            " , po.short_name as mo " +
            " from public.pim_individual pi2 " +
            " join pci_patient_reg ppr on ppr.patient_id = pi2.id " +
            " join pim_organization po on po.id = ppr.clinic_id " +
            " join md_clinic_district mcd on mcd.id = ppr.district_id " +
            " where mcd.id = :distr " +
            " and ppr.type_id = 1 " +
            " and ppr.state_id = 1 " +
            " order by 1 asc ",
            countQuery = "select count(*) " +
            " from public.pim_individual pi2 " +
            " join pci_patient_reg ppr on ppr.patient_id = pi2.id " +
            " join pim_organization po on po.id = ppr.clinic_id " +
            " join md_clinic_district mcd on mcd.id = ppr.district_id " +
            " where mcd.id = :distr " +
            " and ppr.type_id = 1 " +
            " and ppr.state_id = 1 " +
            " order by 1 asc ",
            nativeQuery = true)
    @RestResource
    Page<PatientDto> findAllPatientsFromDistr(@Param("distr")Integer distr, Pageable pageable);
}


