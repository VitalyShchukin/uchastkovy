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
public class DispUchDto {

    @Id
    private Long id;

    private Integer c_diag;
    private Integer d_diag;
    private Integer i_diag;
    private Integer j_diag;
    private Integer k_diag;
}
