package org.logonedigital.mboa_care.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Patient extends Utilisateur{
    private String allergies;
    private String groupSanguin;
    private String antecedents;
}
