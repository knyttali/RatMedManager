package com.krillinator.springSecurityLektion.Meds;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosRepository extends JpaRepository<Diagnos, Long> {
  
}
