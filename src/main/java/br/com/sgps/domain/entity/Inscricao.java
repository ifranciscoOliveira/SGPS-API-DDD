package br.com.sgps.domain.entity;

import br.com.sgps.domain.commons.EtapasEnum;
import br.com.sgps.domain.exception.NegocioException;
import br.com.sgps.domain.valueobject.CandidatoId;
import br.com.sgps.domain.valueobject.InscricaoId;
import br.com.sgps.domain.valueobject.VagaId;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class Inscricao {

    private InscricaoId id;

    private CandidatoId candidatoId;

    private VagaId vagaId;

    private LocalDateTime dataInscricao;

    private EtapasEnum etapaAtual;

    private Boolean aprovado;

    private void setId(InscricaoId id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    private void setCandidatoId(CandidatoId candidatoId) {
        Objects.requireNonNull(candidatoId);
        this.candidatoId = candidatoId;
    }

    private void setVagaId(VagaId vagaId) {
        Objects.requireNonNull(vagaId);
        this.vagaId = vagaId;
    }

    private void setDataInscricao(LocalDateTime dataInscricao) {
        Objects.requireNonNull(dataInscricao);
        this.dataInscricao = dataInscricao;
    }
    private void setAprovado(Boolean aprovado) {
        if(aprovado){
            validarEtapaFinal();
        }
        this.aprovado = aprovado;
    }

    private void validarEtapaFinal() {
        if (!etapaAtual.isFinal()) {
            throw new NegocioException(
                    "A inscrição só pode ser finalizada na etapa final"
            );
        }
    }
    @Builder(builderClassName = "InscricaoExistenteBuild",builderMethodName = "criarExistente")
    public Inscricao(InscricaoId id,
                     CandidatoId candidatoId,
                     VagaId vagaId,
                     LocalDateTime dataInscricao,
                     EtapasEnum etapaAtual,
                     Boolean aprovado) {
        setId(id);
        setCandidatoId(candidatoId);
        setVagaId(vagaId);
        setDataInscricao(dataInscricao);
        alterarEtapaAtual(etapaAtual);
        setAprovado(aprovado);
    }

    public static Inscricao criarNovaInscricao(CandidatoId candidatoId,
                                             VagaId vagaId) {
        return new Inscricao(
                new InscricaoId(),
                candidatoId,
                vagaId,
                LocalDateTime.now(),
                EtapasEnum.INSCRITO,
                null
        );
    }

    public static Inscricao reconstruirInscricao(InscricaoId id,
                                             CandidatoId candidatoId,
                                             VagaId vagaId,
                                             LocalDateTime dataInscricao,
                                             EtapasEnum etapaAtual,
                                             Boolean aprovado) {
        return new Inscricao(
                id,
                candidatoId,
                vagaId,
                dataInscricao,
                etapaAtual,
                aprovado
        );
    }




    public InscricaoId id() {
        return id;
    }

    public CandidatoId candidatoId() {
        return candidatoId;
    }

    public VagaId vagaId() {
        return vagaId;
    }

    public LocalDateTime dataInscricao() {
        return dataInscricao;
    }

    public EtapasEnum etapaAtual() {
        return etapaAtual;
    }

    public Boolean aprovado() {
        return aprovado;
    }
    public void aprovar() {
        validarEtapaFinal();
        setAprovado(true);
    }

    public void reprovar() {
        setAprovado(false);
    }

    public void alterarEtapaAtual(EtapasEnum novaEtapa) {
        Objects.requireNonNull(novaEtapa);
        this.etapaAtual = novaEtapa;
    }

    public void alterarParaProximaEtapa(){
        if(aprovado()){
            throw new NegocioException("Inscrição aprovada não pode avançar para próxima etapa");
        }
        EtapasEnum novaEtapa = Arrays.stream(EtapasEnum.values())
                .filter(e -> e.getOrdem() == etapaAtual().getOrdem() + 1)
                .findFirst()
                .orElseThrow(() -> new NegocioException("Nao existe proxima etapa para a etapa atual: " + etapaAtual()));

        alterarEtapaAtual(novaEtapa);

    }


}
