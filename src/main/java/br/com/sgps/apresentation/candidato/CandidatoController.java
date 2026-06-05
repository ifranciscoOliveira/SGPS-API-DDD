package br.com.sgps.apresentation.candidato;

import br.com.sgps.application.candidato.CandidateInput;
import br.com.sgps.application.candidato.CandidatoAlterarInput;
import br.com.sgps.application.candidato.CandidatoManagementApplicationService;
import br.com.sgps.application.candidato.CandidatoOutPut;
import br.com.sgps.domain.entity.Candidato;
import br.com.sgps.domain.valueobject.CandidatoId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidato")
@RequiredArgsConstructor
public class CandidatoController {

    private final CandidatoManagementApplicationService candidatoManagementApplicationService;


    @GetMapping
    public List<CandidatoOutPut> consultarTodos() {
       return  candidatoManagementApplicationService.consultarTodos()
                .stream()
                .map(CandidatoOutPut::fromDomain)
                .toList();
    }


    @GetMapping("/{idCandidato}")
    public CandidatoOutPut consultarCandidatoPorId(String idCandidato) {

        Candidato candidato = candidatoManagementApplicationService.consultarPorId(
                new CandidatoId( UUID.fromString(idCandidato)));

        return CandidatoOutPut.fromDomain(candidato);


    }

    @PostMapping
    public CandidatoOutPut cadastrarCandidato(@RequestBody CandidateInput input) {
        Candidato candidatoSalvo = candidatoManagementApplicationService.criar(input);
        return CandidatoOutPut.fromDomain(candidatoSalvo);

    }

    @PutMapping("/{idCandidato}")
    public CandidatoOutPut alterarCandidato(@RequestBody CandidatoAlterarInput input, @PathVariable String idCandidato) {
        Candidato candidatoAlterado = candidatoManagementApplicationService.alterar(new CandidatoId(UUID.fromString(idCandidato)), input);
        return CandidatoOutPut.fromDomain(candidatoAlterado);
    }



}
