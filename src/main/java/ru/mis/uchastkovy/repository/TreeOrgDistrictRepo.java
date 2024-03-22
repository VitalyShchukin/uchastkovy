package ru.mis.uchastkovy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.mis.uchastkovy.model.TreeOrgDistrict;

@RepositoryRestResource(collectionResourceRel = "treeOrgDistr", path = "treeOrgDistr")
public interface TreeOrgDistrictRepo extends CrudRepository<TreeOrgDistrict, Long> {


    @Query(value = "select \n" +
            "  row_number() over() as id, * from ( \n" +
            "  (select \n" +
            "    po.id as element_id \n" +
            "  , po.short_name as name \n" +
            "  , cast(null as int4) as parent_id \n" +
            "  , -1 as distr_id \n" +
            "  , null as badge\n" +
            "  , null as status\n" +
            " from public.pim_organization po \n" +
            " where po.id in (SELECT distinct(mcs.clinic_id) FROM public.md_clinic_separation mcs) \n" +
            " order by 2)\n" +
            " union all \n" +
            " (select \n" +
            " (row_number () over ())+(SELECT max(mcs.clinic_id) FROM public.md_clinic_separation mcs) as id \n" +
            " , mcd.name  as name \n" +
            "  , mcs.clinic_id parent_id \n" +
            "  , mcd.id as distr_id\n" +
            "  , case when not exists (select * from public.pci_patient_reg where district_id = mcd.id) then 'пустой участок' \n" +
            " \telse null \n" +
            " \tend as badge  \n" +
            "  , case when not exists (select * from public.pci_patient_reg where district_id = mcd.id) then 'warning'\n" +
            " \telse null \n" +
            " \tend as status\n" +
            " from public.pim_organization po \n" +
            " join public.md_clinic_separation mcs on mcs.clinic_id = po.id \n" +
            " join public.md_clinic_district mcd on mcd.separation_id = mcs.id\n" +
            " order by 2) \n" +
            ") as main",
            countQuery = "select \n" +
                    "  count(*) from ( \n" +
                    "  (select \n" +
                    "    po.id as element_id \n" +
                    "  , po.short_name as name \n" +
                    "  , cast(null as int4) as parent_id \n" +
                    "  , -1 as distr_id \n" +
                    "  , null as badge\n" +
                    "  , null as status\n" +
                    " from public.pim_organization po \n" +
                    " where po.id in (SELECT distinct(mcs.clinic_id) FROM public.md_clinic_separation mcs) \n" +
                    " order by 2)\n" +
                    " union all \n" +
                    " (select \n" +
                    " (row_number () over ())+(SELECT max(mcs.clinic_id) FROM public.md_clinic_separation mcs) as id \n" +
                    " , mcd.name  as name \n" +
                    "  , mcs.clinic_id parent_id \n" +
                    "  , mcd.id as distr_id\n" +
                    "  , case when not exists (select * from public.pci_patient_reg where district_id = mcd.id) then 'пустой участок' \n" +
                    " \telse null \n" +
                    " \tend as badge  \n" +
                    "  , case when not exists (select * from public.pci_patient_reg where district_id = mcd.id) then 'warning'\n" +
                    " \telse null \n" +
                    " \tend as status\n" +
                    " from public.pim_organization po \n" +
                    " join public.md_clinic_separation mcs on mcs.clinic_id = po.id \n" +
                    " join public.md_clinic_district mcd on mcd.separation_id = mcs.id\n" +
                    " order by 2) \n" +
                    ") as main",
            nativeQuery = true)
    @RestResource
    Page<TreeOrgDistrict> findAllDistrictsAndOrgForTree(Pageable pageable);
}
