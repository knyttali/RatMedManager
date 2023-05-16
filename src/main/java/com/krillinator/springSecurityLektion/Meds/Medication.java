package com.krillinator.springSecurityLektion.Meds;


import com.krillinator.springSecurityLektion.rats.Rat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String details;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rat_id")
    private Rat rat;

    public Medication(String name, String details) {
        this.name = name;
        this.details = details;
    }
}
