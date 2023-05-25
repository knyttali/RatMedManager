package com.krillinator.springSecurityLektion.Meds;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosRepository extends JpaRepository<Diagnos, Long> {
    // Här kan du lägga till eventuella anpassade metoder för att söka efter diagnoser eller utföra andra operationer
}
