package com.krillinator.springSecurityLektion.Meds;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicinRepository extends JpaRepository<Medicin, Long>{
    Medicin findById(long id);
}
