package ru.mis.uchastkovy.repository;

public interface OrviRepo {





//    select
//    row_number() over() id
//,to_char(mc.create_date, 'Mon') mnth
//, count(*) cnt
//    from public.pci_patient_reg ppr
//    join public.mc_case mc on mc.patient_id = ppr.patient_id
//    join public.mc_diagnosis md on md.id = mc.main_diagnos_id
//    join public.md_diagnosis md2 on md2.id = md.diagnos_id
//    where ppr.district_id = 136
//    and ppr.state_id = 1
//    and ppr.type_id = 1
//    and md2.code ilike '%I%'
//    and mc.closing_step_id is not null
//    and mc.create_date >= '2021-01-01' --date_trunc('year', current_date)
//    group by 2
//    order by 2
}
