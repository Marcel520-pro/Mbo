package org.logonedigital.mboa_care.repository;

import org.logonedigital.mboa_care.entity.Teleconsultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepo extends JpaRepository<Teleconsultation, String> {

}
