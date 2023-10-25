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


    @Query(value = " select row_number() over () as id, * from (\n" +
            " with org as (\n" +
            " select \n" +
            "    po.id as element_id\n" +
            "  , po.short_name as name\n" +
            "  , cast(null as int4) as parent_id\n" +
            " from public.pim_organization po\n" +
            " where po.id in (SELECT distinct(mcs.clinic_id) FROM public.md_clinic_separation mcs)\n" +
            " )\n" +
            " , distr as (\n" +
            " select \n" +
            "    mcd.id as elemet_id\n" +
            "  , mcd.name as name\n" +
            "  , mcs.clinic_id parent_id\n" +
            " from public.pim_organization po\n" +
            " join public.md_clinic_separation mcs on mcs.clinic_id = po.id\n" +
            " join public.md_clinic_district mcd on mcd.separation_id = mcs.id\n" +
            " )\n" +
            " select * from org\n" +
            " union \n" +
            " select * from distr\n" +
            " ) as main",
            countQuery = " select count(*) from (\n" +
                    " with org as (\n" +
                    " select \n" +
                    "    po.id as element_id\n" +
                    "  , po.short_name as name\n" +
                    "  , cast(null as int4) as parent_id\n" +
                    " from public.pim_organization po\n" +
                    " where po.id in (SELECT distinct(mcs.clinic_id) FROM public.md_clinic_separation mcs)\n" +
                    " )\n" +
                    " , distr as (\n" +
                    " select \n" +
                    "    mcd.id as elemet_id\n" +
                    "  , mcd.name as name\n" +
                    "  , mcs.clinic_id parent_id\n" +
                    " from public.pim_organization po\n" +
                    " join public.md_clinic_separation mcs on mcs.clinic_id = po.id\n" +
                    " join public.md_clinic_district mcd on mcd.separation_id = mcs.id\n" +
                    " )\n" +
                    " select * from org\n" +
                    " union \n" +
                    " select * from distr\n" +
                    " ) as main",
            nativeQuery = true)
    @RestResource
    Page<TreeOrgDistrict> findAllPatientsFromDistr(Pageable pageable);
}
