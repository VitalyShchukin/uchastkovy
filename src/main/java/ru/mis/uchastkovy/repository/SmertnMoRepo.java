package ru.mis.uchastkovy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.mis.uchastkovy.model.SmertnDto;

@RepositoryRestResource(collectionResourceRel = "smertn", path = "smertn")
public interface SmertnMoRepo extends CrudRepository<SmertnDto, Long> {

    @Query(value = " select \n" +
            "\trow_number () over () as id\n" +
            "\t,main.* from \n" +
            "\t\t(select \n" +
            "\t\t po.short_name \n" +
            "\t\t,count(*) \n" +
            "\t\t\tfrom certificates.death_certificate dc\n" +
            "\t\t\tjoin pim_organization po on po.id = dc.org_id \n" +
            "\t\twhere dc.issue_dt between '2023-01-01' and '2024-01-01'\n" +
            "\t\tgroup by 1 \n" +
            "\t\thaving count(*) > 10\n" +
            "\t\torder by 1) as main ",
            countQuery = " select count(*) from\n" +
                    "\t(select \n" +
                    "\t po.short_name \n" +
                    "\t,count(*) \n" +
                    "\t\tfrom certificates.death_certificate dc\n" +
                    "\t\tjoin pim_organization po on po.id = dc.org_id \n" +
                    "\twhere dc.issue_dt between '2023-01-01' and '2024-01-01'\n" +
                    "\tgroup by 1 \n" +
                    "\thaving count(*) > 10\n" +
                    "\torder by 1) as main ",
            nativeQuery = true)
    @RestResource
    Page<SmertnDto> findAllSmertnFromObl(Pageable pageable);
}




