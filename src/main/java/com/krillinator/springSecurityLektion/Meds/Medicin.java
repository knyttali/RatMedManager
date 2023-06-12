package com.krillinator.springSecurityLektion.Meds;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Medicin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String namn;
    private String dosering;
    private boolean completed = false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnos_id")
    private Diagnos diagnos;


    public Medicin(String namn, String dosering) {
        this.namn = namn;
        this.dosering = dosering;
    }

}
