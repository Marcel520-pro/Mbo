package org.logonedigital.mboa_care.security;

import org.logonedigital.mboa_care.entity.Utilisateur;
import org.logonedigital.mboa_care.repository.ProfilRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ProfilRepo profilRepo;

    public CustomUserDetailsService(ProfilRepo profilRepo) {
        this.profilRepo = profilRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = profilRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (utilisateur.getRole() != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().name()));
        }

        return User.builder()
                .username(utilisateur.getEmail())
                .password(utilisateur.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
