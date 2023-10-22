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
public class GenderAndChildDto {

    @Id
    private Long id;

    private Integer countAll;
    private Integer countChild;
    private Integer countMan;
    private Integer countWoman;
}
