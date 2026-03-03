package org.logonedigital.mboa_care.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Medecin extends Utilisateur{
    private String specialite;
    private boolean disponible;
}
