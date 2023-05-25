package com.krillinator.springSecurityLektion.Meds;

import com.krillinator.springSecurityLektion.Meds.Diagnos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosRepository extends JpaRepository<Diagnos, Long> {
    // Här kan du lägga till eventuella anpassade metoder för att söka efter diagnoser eller utföra andra operationer
}
