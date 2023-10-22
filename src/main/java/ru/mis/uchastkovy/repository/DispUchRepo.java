package ru.mis.uchastkovy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.mis.uchastkovy.model.DispUchDto;
import ru.mis.uchastkovy.model.DistrictDto;

@RepositoryRestResource(collectionResourceRel = "disp", path = "disp")
public interface DispUchRepo extends CrudRepository<DispUchDto, Long>{

    @Query(value = "with main as (\n" +
            "select " +
            " pd.id " +
            " , mnr.code d_code " +
            " from public.pci_patient_reg ppr " +
            " join public.pim_individual pi2 on pi2.id = ppr.patient_id " +
            " join public.pci_dispensary pd on pd.patient_id = pi2.id " +
            " join md_nosol_registr mnr on mnr.id = pd.nosol_registr_id " +
            " where ppr.district_id = :distr " +
            " and reg_out_dt is null " +
            " )" +
            " select " +
            " -1 as id " +
            " , (select count(*) from main where d_code ilike '%c%') as c_diag " +
            " , (select count(*) from main where d_code ilike '%d%') as d_diag " +
            " , (select count(*) from main where d_code ilike '%i%') as i_diag " +
            " , (select count(*) from main where d_code ilike '%j%') as j_diag " +
            " , (select count(*) from main where d_code ilike '%k%') as k_diag " +
            " , (select count(*) from main) as all_diags " +
            " from main " +
            " group by 1 ",
            countQuery = "select 1",
            nativeQuery = true)
    @RestResource
    Page<DispUchDto> findAllDispUchPatients(Pageable pageable);
}