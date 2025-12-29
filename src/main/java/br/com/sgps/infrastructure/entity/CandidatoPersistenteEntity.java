package br.com.sgps.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "candidato")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoPersistenteEntity {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 14, unique = true)
    private String cpf;
    @Column(nullable = false, length = 255)
    private String nome;
    @Column(nullable = false, length = 255, unique = true)
    private String email;
    @Column(nullable = false, length = 20)
    private String telefone;
    @Column(nullable = false)
    private LocalDate dataNascimento;
}
