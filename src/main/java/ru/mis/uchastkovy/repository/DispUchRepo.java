package ru.mis.uchastkovy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.mis.uchastkovy.model.DispUchDto;
import ru.mis.uchastkovy.model.DistrictDto;

@RepositoryRestResource(collectionResourceRel = "disp", path = "disp")
public interface DispUchRepo extends CrudRepository<DispUchDto, Long>{

    @Query(value = "select " +
            " row_number() over () as id, " +
            " case " +
            "  when mnr.code ilike '%I%' then 'ССЗ' " +
            "  when mnr.code ilike '%G%' then 'ОНКО' " +
            "  when mnr.code ilike '%M%' then 'АКИНЕО' " +
            "  else 'иные' " +
            " end as diag_group " +
            "  , count(*) as count_pats " +
            " from public.pci_patient_reg ppr " +
            " join public.pim_individual pi2 on pi2.id = ppr.patient_id " +
            " join public.pci_dispensary pd on pd.patient_id = pi2.id " +
            " join md_nosol_registr mnr on mnr.id = pd.nosol_registr_id " +
            " where ppr.district_id = :distr " +
            " and reg_out_dt is null " +
            " group by 2 " +
            " order by 1 ",
            countQuery = "select count(*) from( " +
                    " select " +
                    " row_number() over () as id, " +
                    " case " +
                    "  when mnr.code ilike '%I%' then 'ССЗ' " +
                    "  when mnr.code ilike '%G%' then 'ОНКО' " +
                    "  when mnr.code ilike '%M%' then 'АКИНЕО' " +
                    "  else 'иные' " +
                    " end as diag_group " +
                    "  , count(*) as count_pats " +
                    " from public.pci_patient_reg ppr " +
                    " join public.pim_individual pi2 on pi2.id = ppr.patient_id " +
                    " join public.pci_dispensary pd on pd.patient_id = pi2.id " +
                    " join md_nosol_registr mnr on mnr.id = pd.nosol_registr_id " +
                    " where ppr.district_id = :distr " +
                    " and reg_out_dt is null " +
                    " group by 2 " +
                    " order by 1) as rs",
            nativeQuery = true)
    @RestResource
    Page<DispUchDto> findAllDispUchPatients(@Param("distr")Integer distr, Pageable pageable);
}