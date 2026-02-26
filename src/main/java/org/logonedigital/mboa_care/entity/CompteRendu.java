package org.logonedigital.mboa_care.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "CompteRendu ")

public class CompteRendu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String id ;
    private String motif ;
    private LocalDateTime dateHeure ;
}
