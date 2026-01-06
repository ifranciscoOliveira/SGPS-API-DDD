package br.com.sgps.infrastructure.provider;

import br.com.sgps.domain.entity.Vaga;
import br.com.sgps.domain.repository.VagaRepositoryDomain;
import br.com.sgps.domain.valueobject.VagaId;
import br.com.sgps.infrastructure.assembler.VagaPersistenceEntityAssembler;
import br.com.sgps.infrastructure.entity.VagaPersistenceEntity;
import br.com.sgps.infrastructure.repository.VagaPersistenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VagaPersistenceProvider implements VagaRepositoryDomain {

    private final VagaPersistenceRepository vagaPersistenceRepository;
    private final VagaPersistenceEntityAssembler vagaPersistenceEntityAssembler;

    @Override
    public boolean existeTituloCadastrado(String titulo, VagaId id) {
        return vagaPersistenceRepository.existTituloCadastrado(titulo,id.value());
    }

    @Override
    public boolean existe(VagaId id) {
        return vagaPersistenceRepository.existsById(id.value());
    }

    @Override
    public Optional<Vaga> conusltarPorId(VagaId id) {
        return Optional.empty();
    }

    @Override
    public void persistir(Vaga vaga) {
        UUID id = vaga.id().value();
        vagaPersistenceRepository.findById(id)
                .ifPresentOrElse((vagaEncontrada) ->
                        alterar(vaga, vagaEncontrada),
                        () -> salvar(vaga));
    }

    private void alterar(Vaga vaga, VagaPersistenceEntity vagaEncontrada) {
        VagaPersistenceEntity vagaPersistenceEntity = vagaPersistenceEntityAssembler.fromDomain(vaga);
        vagaPersistenceEntity.setId(vagaEncontrada.getId());
        vagaPersistenceRepository.saveAndFlush(vagaPersistenceEntity);
    }

    private void salvar(Vaga vaga) {
        VagaPersistenceEntity vagaPersistenceEntity = vagaPersistenceEntityAssembler.fromDomain(vaga);
        vagaPersistenceRepository.saveAndFlush(vagaPersistenceEntity);

    }


}
