package ru.mis.uchastkovy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.mis.uchastkovy.model.DuchDto;

@RepositoryRestResource(collectionResourceRel = "duchDistr", path = "duchDistr")
public interface DuchRepo extends PagingAndSortingRepository<DuchDto, Long> {

    @Query(value = " select\n" +
            " \t  pi2.id as id\n" +
            " \t, :distr as distr_id\n" +
            "\t, concat_ws(' ', pi2.surname, pi2.name, pi2.patr_name, to_char(pi2.birth_dt, '(dd.mm.yyyy)')) as fio \n" +
            "\t, cast(pd.reg_in_dt as varchar) as start_date\n" +
            "\t, mnr.\"name\" as nosol_name\n" +
            " from pci_patient_reg ppr\n" +
            " join pim_individual pi2 on pi2.id = ppr.patient_id\n" +
            " join pci_dispensary pd on pd.patient_id = pi2.id\n" +
            " join md_nosol_registr mnr on mnr.id = pd.nosol_registr_id\n" +
            " where ppr.district_id = :distr",
            countQuery = " select count(*) " +
                    " from pci_patient_reg ppr\n" +
                    " join pim_individual pi2 on pi2.id = ppr.patient_id\n" +
                    " join pci_dispensary pd on pd.patient_id = pi2.id\n" +
                    " join md_nosol_registr mnr on mnr.id = pd.nosol_registr_id\n" +
                    " where ppr.district_id = :distr",
            nativeQuery = true)
    @RestResource
    Page<DuchDto> findAllDuchInDistr(@Param("distr") Integer distr, Pageable pageable);
}

