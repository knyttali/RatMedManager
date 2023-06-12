package com.krillinator.springSecurityLektion.Meds;

import java.util.ArrayList;
import java.util.List;

import com.krillinator.springSecurityLektion.rats.Rat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Diagnos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String namn;
    private String datum;
    private String beskrivning;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rat_id")
    private Rat rat;
    @OneToMany(mappedBy = "diagnos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Medicin> mediciner;

    public Diagnos(String namn, String datum, String beskrivning) {
        this.namn = namn;
        this.datum = datum;
        this.beskrivning = beskrivning;
    }

}