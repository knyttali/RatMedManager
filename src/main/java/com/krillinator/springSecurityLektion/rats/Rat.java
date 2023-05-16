package com.krillinator.springSecurityLektion.rats;

import java.util.ArrayList;
import java.util.List;

import com.krillinator.springSecurityLektion.Meds.Medication;
import com.krillinator.springSecurityLektion.user.UserModel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /* vanlig info */
    @Column
    private String name;
    @Column
    private int age;
    /* medicinsk info */
    @OneToMany(mappedBy = "rat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medication> medications = new ArrayList<>();
    /* ägare */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private UserModel owner;

    public Rat(String name, int age) {
        this.name = name;
        this.age = age;
    }
}


