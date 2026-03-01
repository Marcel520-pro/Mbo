package org.logonedigital.mboa_care.controllers;

import org.logonedigital.mboa_care.entity.ActivityLog;
import org.logonedigital.mboa_care.service.ActivityLogService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ap1/v1/admin")
public class AdminController {
    private final ActivityLogService activityLogService;

    public AdminController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    /**
     * Récupère tous les logs d'activités globaux (Admin uniquement)
     */
    @GetMapping("/logs")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<ActivityLog>> getAllActivityLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ActivityLog> logs = activityLogService.getAllLogs(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(logs);
    }

    /**
     * Récupère les logs filtrés par action
     */
    @GetMapping("/logs/action/{action}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<ActivityLog>> getLogsByAction(
            @PathVariable String action,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ActivityLog> logs = activityLogService.getLogsByAction(action, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(logs);
    }

    /**
     * Récupère les logs filtrés par utilisateur
     */
    @GetMapping("/logs/username/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<ActivityLog>> getLogsByUsername(
            @PathVariable String username,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ActivityLog> logs = activityLogService.getLogsByUsername(username, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(logs);
    }
}
