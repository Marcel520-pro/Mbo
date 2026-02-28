package com.logonedigital.MBOAcare.repositoy;

import com.logonedigital.MBOAcare.entity.Pharmaci;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentRepo extends JpaRepository<Pharmaci, String>
{ }
