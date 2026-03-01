package com.logonedigital.MBOAcare.repositoy;

import com.logonedigital.MBOAcare.entity.Pharmaci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PharmaciRepo extends JpaRepository<Pharmaci, String> {


    Optional<Pharmaci> findByEmail(String Email);


    // Recherche d'une pharmacie par nom de médicament
    @Query("SELECT DISTINCT s.pharmaci FROM Stock s JOIN s.medicaments m WHERE m.nom = :nomMedicament")
    List<Pharmaci> findPharmaciByMedicamentNom(@Param("nomMedicament") String nomMedicament);

    // Statistique : nombre de médicaments par pharmacie
    @Query("SELECT p.nom, COUNT(m) FROM Pharmaci p JOIN p.stocks s JOIN s.medicaments m GROUP BY p.nom")
    List<Object[]> countMedicamentParPharmaci();
}

