package ru.mis.uchastkovy.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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

}
