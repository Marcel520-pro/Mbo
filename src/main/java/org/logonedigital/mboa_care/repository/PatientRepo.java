package org.logonedigital.mboa_care.repository;

import org.logonedigital.mboa_care.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, String> {
}
