package br.com.sgps.infrastructure.provider;

import br.com.sgps.domain.entity.Inscricao;
import br.com.sgps.domain.repository.InscricaoRepositoryDomain;
import br.com.sgps.infrastructure.assembler.InscricaoPersistenceEntityAssembler;
import br.com.sgps.infrastructure.entity.InscricaoPersistenceEntity;
import br.com.sgps.infrastructure.repository.InscricaoPersistenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InscricaoPersistenceProvider implements InscricaoRepositoryDomain {

    private final InscricaoPersistenceRepository inscricaoPersistenceRepository;
    private final InscricaoPersistenceEntityAssembler inscricaoPersistenceEntityAssembler;


    @Override
    public Optional<Inscricao> consultarPorId(UUID id) {

        var inscricaoEntity = inscricaoPersistenceRepository.findById(id).orElse(null);
        if (inscricaoEntity != null) {
            var inscricao = inscricaoPersistenceEntityAssembler.fromPersistenceEntity(inscricaoEntity);
            return Optional.of(inscricao);
        }
        return Optional.empty();
    }

    @Override
    public boolean existeInscricaoPorCandidatoEPorVaga(UUID candidatoId, UUID vagaId) {
        return inscricaoPersistenceRepository.existsByCandidatoIdAndVagaId(candidatoId,vagaId);
    }

    @Override
    public void persistir(Inscricao inscricao) {
        UUID id = inscricao.id().value();

        inscricaoPersistenceRepository.findById(id)
                .ifPresentOrElse((inscricaoEncontrada) ->
                                alterar(inscricao, inscricaoEncontrada),
                        () -> salvar(inscricao));

    }

    private void salvar(Inscricao inscricao) {
        InscricaoPersistenceEntity persistenteEntity =
                inscricaoPersistenceEntityAssembler.fromDomain(inscricao);
        inscricaoPersistenceRepository.save(persistenteEntity);
    }

    private void alterar(Inscricao inscricao, InscricaoPersistenceEntity inscricaoEncontrada) {
        InscricaoPersistenceEntity persistenteEntity =
                inscricaoPersistenceEntityAssembler.merge(inscricaoEncontrada, inscricao);
        inscricaoPersistenceRepository.save(persistenteEntity);
    }

    @Override
    public List<Inscricao> consultarInscricoesPorCandidatoId(UUID candidatoId) {
        return List.of();
    }

    @Override
    public List<Inscricao> consultarInscricoesPorVagaId(UUID vagaId) {
        return List.of();
    }
}
