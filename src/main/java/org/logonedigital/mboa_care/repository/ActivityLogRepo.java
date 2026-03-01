package org.logonedigital.mboa_care.repository;

import org.logonedigital.mboa_care.entity.ActivityLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepo extends JpaRepository<ActivityLog, String> {
    Page<ActivityLog> findAll(Pageable pageable);
    Page<ActivityLog> findByAction(String action, Pageable pageable);
    Page<ActivityLog> findByUsername(String username, Pageable pageable);
}
