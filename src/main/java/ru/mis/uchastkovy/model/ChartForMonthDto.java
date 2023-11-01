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
public class ChartForMonthDto {

    @Id
    private Long id;

    private Integer mnth;
    private Integer gosp;
    private Integer neotl;
    private Integer distrId;
}
