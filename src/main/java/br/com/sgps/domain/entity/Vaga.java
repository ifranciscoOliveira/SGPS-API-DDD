package br.com.sgps.domain.entity;

import br.com.sgps.domain.valueobject.VagaId;

import java.time.LocalDate;
import java.util.UUID;

public class Vaga {

    private VagaId id;
    private String titulo;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String status;
    private String observacao;




}
