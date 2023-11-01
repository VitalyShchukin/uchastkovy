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

    @Query(value = "select \n" +
            " pi2.id as id \n" +
            " , concat_ws(' ', pi2.surname, pi2.name, pi2.patr_name, to_char(pi2.birth_dt, '(dd.mm.yyyy)')) as fio \n" +
            " , mcd.name as distr \n" +
            " , mcd.id as distr_id \n" +
            " , po.short_name as mo \n" +
            " , adr__get_element_as_text(ppa.addr_id,'(3,s,0)(4,s,0)(5,s,0)(6,s,0)(7,s,0)(8,s,0)') as addr\n" +
            " , pic.value as contact\n" +
            " from public.pim_individual pi2 \n" +
            " join pci_patient_reg ppr on ppr.patient_id = pi2.id \n" +
            " join pim_organization po on po.id = ppr.clinic_id \n" +
            " join md_clinic_district mcd on mcd.id = ppr.district_id \n" +
            " left join pim_party_address ppa on ppa.party_id = pi2.id\n" +
            " left join public.pim_indiv_contact pic on pic.indiv_id = pi2.id\n" +
            " where mcd.id = :distr \n" +
            " and ppr.type_id = 1 \n" +
            " and ppr.state_id = 1 \n" +
            " order by 1 asc ",
            countQuery = "select count(*)" +
                    " from public.pim_individual pi2 \n" +
                    " join pci_patient_reg ppr on ppr.patient_id = pi2.id \n" +
                    " join pim_organization po on po.id = ppr.clinic_id \n" +
                    " join md_clinic_district mcd on mcd.id = ppr.district_id \n" +
                    " left join pim_party_address ppa on ppa.party_id = pi2.id\n" +
                    " left join public.pim_indiv_contact pic on pic.indiv_id = pi2.id\n" +
                    " where mcd.id = :distr \n" +
                    " and ppr.type_id = 1 \n" +
                    " and ppr.state_id = 1 \n" +
                    " order by 1 asc ",
            nativeQuery = true)
    @RestResource
    Page<PatientDto> findAllPatientsFromDistr(@Param("distr")Integer distr, Pageable pageable);
}


