package org.logonedigital.mboa_care.repository;

import org.logonedigital.mboa_care.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location, String> {
}
