package ru.mis.uchastkovy.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PatientDto {

    @Id
    private Long id;

    private String fio;
    private String distr;
    private Integer distrId;
    private String mo;
    private String addr;
    private String contact;
}
