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
public class DuchDto {

    @Id
    private Long id;

    private Integer distrId;
    private String fio;
    private String startDate;
    private String nosolName;
}
