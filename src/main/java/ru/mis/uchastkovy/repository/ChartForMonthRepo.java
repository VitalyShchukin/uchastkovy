package ru.mis.uchastkovy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.mis.uchastkovy.model.ChartForMonthDto;

@RepositoryRestResource(collectionResourceRel = "forChartMonth", path = "forChartMonth")
public interface ChartForMonthRepo extends CrudRepository<ChartForMonthDto, Long> {

    @Query(value = "select\n" +
            "\trow_number() over () as id\n" +
            "\t, close_month mnth\n" +
            "\t, count(t1) gosp\n" +
            "\t, count(t2) neotl\n" +
            "\t, :distr as distr_id\n" +
            "\tfrom (\n" +
            "\twith main as (\n" +
            "\t\tselect *\n" +
            "\t\tfrom public.mc_case mc\n" +
            "\t\twhere mc.closing_step_id is not null \n" +
            "\t\tand mc.patient_id in (select patient_id from public.pci_patient_reg ppr\n" +
            "\t\t    where district_id = :distr\n" +
            "\t\t    and state_id = 1\n" +
            "\t\t\tand type_id = 1)\n" +
            "\t\tand mc.close_date between '2021-01-01' and '2022-01-01'\n" +
            "\t\t)\n" +
            "\t\t, t2 as (\n" +
            "\t\tselect \n" +
            "\t\textract('month' from close_date) close_month\n" +
            "\t\t, case_type_id t1\n" +
            "\t\t, cast(null as int4) as t2 from main \n" +
            "\t\twhere case_type_id = 2\n" +
            "\t\t)\n" +
            "\t\t, t3 as (\n" +
            "\t\tselect \n" +
            "\t\textract('month' from close_date) close_month\n" +
            "\t\t, cast(null as int4) as t1\n" +
            "\t\t, case_type_id as t2 from main \n" +
            "\t\twhere case_type_id = 3\n" +
            "\t\t)\n" +
            "\t\tselect * from t2\n" +
            "\t\tunion all \n" +
            "\t\tselect * from t3\n" +
            ") as m \n" +
            "group by 2\n" +
            "order by 2",
            countQuery = "\tselect count(*) from (\n" +
                    "\twith main as (\n" +
                    "\t\tselect *\n" +
                    "\t\tfrom public.mc_case mc\n" +
                    "\t\twhere mc.closing_step_id is not null \n" +
                    "\t\tand mc.patient_id in (select patient_id from public.pci_patient_reg ppr\n" +
                    "\t\t    where district_id = :distr\n" +
                    "\t\t    and state_id = 1\n" +
                    "\t\t\tand type_id = 1)\n" +
                    "\t\tand mc.close_date between '2021-01-01' and '2022-01-01'\n" +
                    "\t\t)\n" +
                    "\t\t, t2 as (\n" +
                    "\t\tselect \n" +
                    "\t\textract('month' from close_date) close_month\n" +
                    "\t\t, case_type_id t1\n" +
                    "\t\t, cast(null as int4) as t2 from main \n" +
                    "\t\twhere case_type_id = 2\n" +
                    "\t\t)\n" +
                    "\t\t, t3 as (\n" +
                    "\t\tselect \n" +
                    "\t\textract('month' from close_date) close_month\n" +
                    "\t\t, cast(null as int4) as t1\n" +
                    "\t\t, case_type_id as t2 from main \n" +
                    "\t\twhere case_type_id = 3\n" +
                    "\t\t)\n" +
                    "\t\tselect * from t2\n" +
                    "\t\tunion all \n" +
                    "\t\tselect * from t3\n" +
                    ") as m ",
            nativeQuery = true)
    @RestResource
    Page<ChartForMonthDto> getDataForChartOfMonthFromDistr(@Param("distr") Integer distr, Pageable pageable);
}
