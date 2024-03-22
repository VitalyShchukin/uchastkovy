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
public class TreeOrgDistrict {

    @Id
    private Long id;

    private Integer elementId;
    private String name;
    private Integer parentId;
    private Integer distrId;
    private String badge;
    private String status;
}
