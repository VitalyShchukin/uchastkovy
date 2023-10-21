package ru.mis.uchastkovy.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mis.uchastkovy.model.sqlModel.PatientDto;

import java.util.List;

@Repository
public class Rep {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void findNsiDepartKind(Integer code, String name, boolean ignoreName) {

        StringBuilder sql = new StringBuilder("select name " +
                "from public.test where id = :id ");
        if (!ignoreName) {
            sql.append("and name = :name");
        }
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", code)
                .addValue("name", name);

//        jdbcTemplate.queryForObject(sql.toString(), params, mapperNsiDepartKind);
        jdbcTemplate.queryForObject(sql.toString(), params, String.class);

    }

//    private final RowMapper mapperNsiDepartKind = (rs, rowNum) -> NsiDepartKind.builder()
//            .id(rs.getInt("id"))
//            .departKind(rs.getString("name"))
//            .build();

    public List<PatientDto> findPatsFromDistr(Integer distrId) {

        String sql = "select \n" +
                "\tconcat_ws(' ', pi2.surname, pi2.name, pi2.patr_name, to_char(pi2.birth_dt, '(dd.mm.yyyy)'))\t\n" +
                "\t, mcd.name\n" +
                "\t, po.short_name \n" +
                "from public.pim_individual pi2 \n" +
                "join pci_patient_reg ppr on ppr.patient_id = pi2.id \n" +
                "join pim_organization po on po.id = ppr.clinic_id \n" +
                "join md_clinic_district mcd on mcd.id = ppr.district_id \n" +
                "where mcd.id = :distr_id\n" +
                "and ppr.type_id = 1\n" +
                "and ppr.state_id = 1";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("distr_id", distrId);

        final RowMapper mapperPats = (rs, rowNum) -> PatientDto.builder()
                .fio(rs.getString(1))
                .distr(rs.getString(2))
                .mo(rs.getString(3))
                .build();

        return (List<PatientDto>) jdbcTemplate.query(sql, params, mapperPats);
    }

}
