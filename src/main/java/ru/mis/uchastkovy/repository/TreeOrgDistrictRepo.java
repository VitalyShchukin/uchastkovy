package ru.mis.uchastkovy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.mis.uchastkovy.model.PatientDto;
import ru.mis.uchastkovy.model.TreeOrgDistrict;

@RepositoryRestResource(collectionResourceRel = "treeOrgDistr", path = "treeOrgDistr")
public interface TreeOrgDistrictRepo extends CrudRepository<TreeOrgDistrict, Long> {


    @Query(value = " select row_number() over() as id, * from ( " +
            "  select " +
            "    po.id as element_id " +
            "  , po.short_name as name " +
            "  , cast(null as int4) as parent_id " +
            "  , -1 as distr_id " +
            " from public.pim_organization po " +
            " where po.id in (SELECT distinct(mcs.clinic_id) FROM public.md_clinic_separation mcs) " +
            " union all " +
            " select " +
            " (row_number () over ())+(SELECT max(mcs.clinic_id) FROM public.md_clinic_separation mcs) as id " +
            " , case " +
            " when not exists (select * from public.pci_patient_reg where district_id = mcd.id) then concat(mcd.name, '(пустой)') " +
            " else mcd.name " +
            " end as name " +
            "  , mcs.clinic_id parent_id " +
            "  , mcd.id as distr_id " +
            " from public.pim_organization po " +
            " join public.md_clinic_separation mcs on mcs.clinic_id = po.id " +
            " join public.md_clinic_district mcd on mcd.separation_id = mcs.id " +
            ") as main",
            countQuery = " select count(*) from ( " +
                    "  select " +
                    "    po.id as element_id " +
                    "  , po.short_name as name " +
                    "  , cast(null as int4) as parent_id " +
                    "  , -1 as distr_id " +
                    " from public.pim_organization po " +
                    " where po.id in (SELECT distinct(mcs.clinic_id) FROM public.md_clinic_separation mcs) " +
                    " union all " +
                    " select " +
                    " (row_number () over ())+(SELECT max(mcs.clinic_id) FROM public.md_clinic_separation mcs) as id " +
                    " , case " +
                    " when not exists (select * from public.pci_patient_reg where district_id = mcd.id) then concat(mcd.name, '(пустой)') " +
                    " else mcd.name " +
                    " end as name " +
                    "  , mcs.clinic_id parent_id " +
                    "  , mcd.id as distr_id " +
                    " from public.pim_organization po " +
                    " join public.md_clinic_separation mcs on mcs.clinic_id = po.id " +
                    " join public.md_clinic_district mcd on mcd.separation_id = mcs.id " +
                    ") as main",
            nativeQuery = true)
    @RestResource
    Page<TreeOrgDistrict> findAllPatientsFromDistr(Pageable pageable);
}
