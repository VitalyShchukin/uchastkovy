package ru.mis.uchastkovy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.mis.uchastkovy.model.GenderAndChildDto;
import ru.mis.uchastkovy.model.PatientDto;

@RepositoryRestResource(collectionResourceRel = "countPatients", path = "countPatients")
public interface GenderAndChildRepo extends PagingAndSortingRepository<GenderAndChildDto, Long> {

    @Query(value = "with " +
            " main as " +
            "  (select " +
            "   pi2.gender_id as p_gender " +
            "   , date_part('year',age(current_timestamp, pi2.birth_dt)) as p_age " +
            "  from public.pci_patient_reg ppr " +
            "  join public.pim_individual pi2 on pi2.id = ppr.patient_id " +
            "  where ppr.district_id = :distr " +
            "  and ppr.state_id = 1 " +
            "  and ppr.type_id = 1) " +
            " select " +
            " -1 as id " +
            " , (select count(*) from main) as cnt_all " +
            " , (select count(*) from main where p_age < 18) cnt_child " +
            " , (select count(*) from main where p_gender = 1 and p_age >= 18) cnt_m " +
            " , (select count(*) from main where p_gender = 2 and p_age >= 18) cnt_w " +
            " from main " +
            " group by 1 ",
            countQuery = "select 1",
            nativeQuery = true)
    @RestResource
    Page<PatientDto> countPatientsByGroupsFromDistr(@Param("distr")Integer distr, Pageable pageable);
}
