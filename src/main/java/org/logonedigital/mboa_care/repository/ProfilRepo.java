package org.logonedigital.mboa_care.repository;

import org.logonedigital.mboa_care.entity.Role;
import org.logonedigital.mboa_care.entity.Utilisateur;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfilRepo extends JpaRepository<Utilisateur, String> {
    boolean existsByEmail(String email);
    List<Utilisateur> findByRole(Role role);
}
