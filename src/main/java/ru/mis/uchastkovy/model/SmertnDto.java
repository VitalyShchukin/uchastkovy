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
public class SmertnDto {

    @Id
    private Long id;

    private String shortName;
    private Integer count;
}