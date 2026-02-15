package org.logonedigital.mboa_care.repository;

import org.logonedigital.mboa_care.entity.Role;
import org.logonedigital.mboa_care.entity.Utilisateur;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfilRepo extends JpaRepository<Utilisateur, String> {
    boolean existsByEmail(String email);
    List<Utilisateur> findByRole(Role role);
}
