package org.logonedigital.mboa_care;

import org.logonedigital.mboa_care.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers("/login", "/register", "/", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/health").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/ap1/v1/profil-utilisateur/create_patient", "/ap1/v1/profil-utilisateur/create_medecin").permitAll()
                        // Patient endpoints - require PATIENT role
                        .requestMatchers("/ap1/v1/profil-utilisateur/get_patient_by_id/**", "/ap1/v1/profil-utilisateur/liste_patients", "/ap1/v1/profil-utilisateur/modify_patient/**").hasAnyRole("PATIENT", "ADMIN")
                        // Medecin endpoints - require MEDECIN role
                        .requestMatchers("/ap1/v1/profil-utilisateur/get_medecin_by_id/**", "/ap1/v1/profil-utilisateur/liste_medecins", "/ap1/v1/profil-utilisateur/modify_medecin/**").hasAnyRole("MEDECIN", "ADMIN")
                        // Delete endpoints - require ADMIN role
                        .requestMatchers("/ap1/v1/profil-utilisateur/delete_profile").hasRole("ADMIN")
                        // Any other request requires authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }
}

