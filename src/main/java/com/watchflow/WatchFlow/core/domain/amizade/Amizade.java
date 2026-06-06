package com.watchflow.watchflow.core.domain.amizade;

import com.watchflow.watchflow.core.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "amizades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Amizade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "solicitante_id", nullable = false)
    private Usuario solicitante;

    @ManyToOne
    @JoinColumn(name = "solicitado_id", nullable = false)
    private Usuario solicitado;

    @Enumerated(EnumType.STRING)
    private StatusAmizade status;

    private LocalDateTime dataSolicitacao;
}