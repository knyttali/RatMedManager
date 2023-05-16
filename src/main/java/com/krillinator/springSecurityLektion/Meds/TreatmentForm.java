package com.krillinator.springSecurityLektion.Meds;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreatmentForm {
    private String diagnosis;
    private String medication;
    private Long ratId;
   
}

