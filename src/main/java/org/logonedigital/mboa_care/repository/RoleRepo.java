package org.logonedigital.mboa_care.repository;

import org.logonedigital.mboa_care.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, String> {
    Optional<Role> findByNomRole(String nomRole);
    boolean existsByNomRole(String nomRole);}
