package org.logonedigital.mboa_care.security;

import lombok.extern.slf4j.Slf4j;
import org.logonedigital.mboa_care.entity.Location;
import org.logonedigital.mboa_care.entity.Patient;
import org.logonedigital.mboa_care.entity.Role;
import org.logonedigital.mboa_care.repository.ProfilRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class SecurityInitializer implements CommandLineRunner {

    private final ProfilRepo profilRepo;
    private final PasswordEncoder passwordEncoder;

    public SecurityInitializer(ProfilRepo profilRepo, PasswordEncoder passwordEncoder) {
        this.profilRepo = profilRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize default user jerry if it doesn't exist
        String defaultEmail = "jerry@mboacare.com";
        if (!profilRepo.existsByEmail(defaultEmail)) {
            Location location = Location.builder()
                    .ville("Douala")
                    .quartier("Akwa")
                    .build();

            Patient patient = new Patient(
                    "Jerry",
                    "237671234567",
                    defaultEmail,
                    passwordEncoder.encode("06jan2008"),
                    location
            );
            patient.setCreatedAt(LocalDate.now());
            patient.setRole(Role.PATIENT);
            profilRepo.save(patient);
            log.info("Default user 'jerry' created successfully with email: {}", defaultEmail);
        } else {
            log.info("Default user 'jerry' already exists");
        }
    }
}
