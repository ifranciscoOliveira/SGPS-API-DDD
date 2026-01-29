package br.com.sgps.apresentation.candidato;

import br.com.sgps.application.candidato.CandidatoAlterarInput;
import br.com.sgps.application.candidato.CandidatoManagementApplicationService;
import br.com.sgps.application.candidato.CandidatoOutPut;
import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.valueobject.CandidatoId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/candidato")
@RequiredArgsConstructor
public class CandidatoController {

    private final CandidatoManagementApplicationService candidatoManagementApplicationService;

    @GetMapping("/{idCandidato}")
    public CandidatoOutPut consultarCandidatoPorId(String idCandidato) {

        Candidato candidato = candidatoManagementApplicationService.consultarPorId(
                new CandidatoId( UUID.fromString(idCandidato)));

        return CandidatoOutPut.fromDomain(candidato);


    }

    @PostMapping
    public CandidatoOutPut cadastrarCandidato(CandidatoAlterarInput input) {
        return null;
    }



}
