package br.com.sgps.domain.repository;

import br.com.sgps.application.vaga.VagaFiltro;
import br.com.sgps.domain.commons.Pagina;
import br.com.sgps.domain.commons.Paginacao;
import br.com.sgps.domain.entity.Vaga;
import br.com.sgps.domain.valueobject.VagaId;

import java.util.List;
import java.util.Optional;

public interface VagaRepositoryDomain {

    boolean existeTituloCadastrado(String titulo, VagaId id);

    boolean existe(VagaId id);

    Vaga persistir(Vaga vaga);

    Optional<Vaga> consultarPorId(VagaId id);

    List<Vaga> consultarTodos();

    Pagina<Vaga> listar(VagaFiltro vagaFiltro, Paginacao paginacao);
}
