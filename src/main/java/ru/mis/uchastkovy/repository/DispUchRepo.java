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

    @Query(value = "select \n" +
            " row_number() over () as id, \n" +
            " case \n" +
            "  when mnr.code ilike '%I%' then 'ССЗ' \n" +
            "  when mnr.code ilike '%С%' then 'Злокач. новообразования' \n" +
            "  when mnr.code ilike '%F%' then 'Психические' \n" +
            "  when mnr.code ilike '%А%' then 'Инфекционные' \n" +
            "  when mnr.code ilike '%D%' then 'Доброкач. новообразования' \n" +
            "  when mnr.code ilike '%P%' then 'АКИНЕО' \n" +
            "  when mnr.code ilike '%L%' then 'Болезни кожи' \n" +
            "  else 'иные' \n" +
            " end as diag_group \n" +
            "  , count(*) as count_pats \n" +
            " from public.pci_patient_reg ppr \n" +
            " join public.pim_individual pi2 on pi2.id = ppr.patient_id \n" +
            " join public.pci_dispensary pd on pd.patient_id = pi2.id \n" +
            " join md_nosol_registr mnr on mnr.id = pd.nosol_registr_id \n" +
            " where ppr.district_id = :distr \n" +
            " and reg_out_dt is null \n" +
            " group by 2 \n" +
            " order by 1 ",
            countQuery = "select count(*) from( " +
                    " select \n" +
                    " row_number() over () as id, \n" +
                    " case \n" +
                    "  when mnr.code ilike '%I%' then 'ССЗ' \n" +
                    "  when mnr.code ilike '%С%' then 'Злокач. новообразования' \n" +
                    "  when mnr.code ilike '%F%' then 'Психические' \n" +
                    "  when mnr.code ilike '%А%' then 'Инфекционные' \n" +
                    "  when mnr.code ilike '%D%' then 'Доброкач. новообразования' \n" +
                    "  when mnr.code ilike '%P%' then 'АКИНЕО' \n" +
                    "  when mnr.code ilike '%L%' then 'Болезни кожи' \n" +
                    "  else 'иные' \n" +
                    " end as diag_group \n" +
                    "  , count(*) as count_pats \n" +
                    " from public.pci_patient_reg ppr \n" +
                    " join public.pim_individual pi2 on pi2.id = ppr.patient_id \n" +
                    " join public.pci_dispensary pd on pd.patient_id = pi2.id \n" +
                    " join md_nosol_registr mnr on mnr.id = pd.nosol_registr_id \n" +
                    " where ppr.district_id = :distr \n" +
                    " and reg_out_dt is null \n" +
                    " group by 2 \n" +
                    " order by 1 ) as rs",
            nativeQuery = true)
    @RestResource
    Page<DispUchDto> findAllDispUchPatients(@Param("distr")Integer distr, Pageable pageable);
}