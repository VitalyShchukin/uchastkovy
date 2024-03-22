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

    @Query(value = "with main as (select " +
            "  pi2.gender_id  as gender " +
            "  , date_part('year',age(current_timestamp, pi2.birth_dt)) years_old " +
            "  from public.pci_patient_reg ppr " +
            "  join public.pim_individual pi2 on pi2.id = ppr.patient_id " +
            "  where ppr.district_id = :distr " +
            "  and ppr.state_id = 1 " +
            "  and ppr.type_id = 1) " +
            "  , by_group as ( " +
            "  select " +
            "   case " +
            "   when main.gender = 1 and years_old >= 18 then 'муж' \n" +
            "   when main.gender = 2 and years_old >= 18 then 'жен' \n" +
            "   when years_old < 18 then 'дети' \n" +
            "  end as gender " +
            "  , count(*) pat_count\n" +
            "  from main \n" +
            "  group by 1\n" +
            "  order by 2) \n" +
            "  , full_cnt as (\n" +
            "  \tselect \n" +
            "  \tcast(4 as int4) as id\n" +
            "\t,cast('всего' as text) as gender\n" +
            "\t, count(*) pat_count\n" +
            "  \tfrom main) \n" +
            "  select * from full_cnt \n" +
            "  union \n" +
            "  select row_number() over () as id,* from by_group\n" +
            "  order by 1",
            countQuery = "select count(*) from\n" +
                    "  (with main as (select \n" +
                    "\t pi2.gender_id  as gender\n" +
                    "\t , date_part('year',age(current_timestamp, pi2.birth_dt)) years_old\n" +
                    "  from public.pci_patient_reg ppr \n" +
                    "  join public.pim_individual pi2 on pi2.id = ppr.patient_id \n" +
                    "  where ppr.district_id = :distr \n" +
                    "  and ppr.state_id = 1 \n" +
                    "  and ppr.type_id = 1) \n" +
                    "  , by_group as (\n" +
                    "  select \t \n" +
                    "  \tcase \n" +
                    "\t\twhen main.gender = 1 and years_old >= 18 then 'муж' \n" +
                    "\t\twhen main.gender = 2 and years_old >= 18 then 'жен' \n" +
                    "\t\twhen years_old < 18 then 'дети' \n" +
                    "\tend as gender\n" +
                    "  , count(*) pat_count\n" +
                    "  from main \n" +
                    "  group by 1\n" +
                    "  order by 2) \n" +
                    "  , full_cnt as (\n" +
                    "  \tselect \n" +
                    "  \tcast(4 as int4) as id\n" +
                    "\t,cast('всего' as text) as gender\n" +
                    "\t, count(*) pat_count\n" +
                    "  \tfrom main) \n" +
                    "  select * from full_cnt \n" +
                    "  union \n" +
                    "  select row_number() over () as id,* from by_group\n" +
                    "  order by 1) as rs",
            nativeQuery = true)
    @RestResource
    Page<GenderAndChildDto> countPatientsByGroupsFromDistr(@Param("distr")Integer distr, Pageable pageable);
}
