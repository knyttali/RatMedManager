package com.krillinator.springSecurityLektion.Meds;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Treatment {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String notes;
}