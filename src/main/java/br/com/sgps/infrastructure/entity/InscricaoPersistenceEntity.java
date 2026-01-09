package br.com.sgps.infrastructure.entity;

import br.com.sgps.domain.commons.EtapasEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "inscricao")
@Data
@Builder
public class InscricaoPersistenceEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_candidato", nullable = false)
    private CandidatoPersistenteEntity candidato;

    @ManyToOne
    @JoinColumn(name = "id_vaga", nullable = false)
    private VagaPersistenceEntity vaga;


    @Column(name = "data_inscricao")
    private LocalDateTime dataInscricao;

    @Enumerated(EnumType.STRING)
    @Column(name ="etapa_atual")
    private EtapasEnum etapaAtual;

    @Column(name ="aprovado")
    private Boolean aprovado;
}
