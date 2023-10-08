package ru.mis.uchastkovy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Uch {

    private Long id;
    private String fio;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    private boolean disp;

}
