package ru.mis.uchastkovy.model.sqlModel;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DistrictDto {

    @Id
    private Long id;

    private String distr;
    private String separation;
    private String distr_type;
}
