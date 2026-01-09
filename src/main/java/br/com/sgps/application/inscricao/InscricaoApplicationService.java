package br.com.sgps.application.inscricao;


import br.com.sgps.domain.repository.InscricaoRepositoryDomain;
import br.com.sgps.domain.service.InscricaoServiceDomain;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InscricaoApplicationService {

    private final InscricaoServiceDomain inscricaoServiceDomain;
    private final InscricaoRepositoryDomain inscricaoRepositoryDomain;

    @Transactional
    public void inscreverCandidatoEmVaga(UUID candidatoId, UUID vagaId) {
        inscricaoServiceDomain.realizarInscricao(candidatoId, vagaId);
    }

}
