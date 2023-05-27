package com.krillinator.springSecurityLektion.rats;

import java.util.ArrayList;
import java.util.List;

import com.krillinator.springSecurityLektion.Meds.Diagnos;
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
    @Column
    private String personality;
    /* medicinsk info */
    @OneToMany(mappedBy = "rat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Diagnos> diagnoser;
    /* ägare */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private UserModel owner;

    public Rat(String name, int age) {
        this.name = name; 
        this.age = age;
        this.diagnoser = new ArrayList<>();
    }
}


