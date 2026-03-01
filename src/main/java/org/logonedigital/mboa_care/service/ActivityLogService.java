package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.entity.ActivityLog;
import org.logonedigital.mboa_care.repository.ActivityLogRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ActivityLogService {
    private final ActivityLogRepo activityLogRepository;

    public ActivityLogService(ActivityLogRepo activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    /**
     * Récupère tous les logs d'activités avec pagination
     */
    public Page<ActivityLog> getAllLogs(int page, int size) {
        return activityLogRepository.findAll(
                PageRequest.of(page, size, Sort.by("dateAction").descending())
        );
    }

    /**
     * Récupère les logs filtrés par action
     */
    public Page<ActivityLog> getLogsByAction(String action, int page, int size) {
        return activityLogRepository.findByAction(action,
                PageRequest.of(page, size, Sort.by("dateAction").descending())
        );
    }

    /**
     * Récupère les logs filtrés par utilisateur
     */
    public Page<ActivityLog> getLogsByUsername(String username, int page, int size) {
        return activityLogRepository.findByUsername(username,
                PageRequest.of(page, size, Sort.by("dateAction").descending())
        );
    }

    /**
     * Enregistre une action dans les logs
     */
    public void logAction(String action, String username) {
        ActivityLog log = ActivityLog.builder()
                .action(action)
                .username(username)
                .dateAction(java.time.LocalDateTime.now())
                .build();
        activityLogRepository.save(log);
    }
}
