package br.com.sgps.apresentation.inscricao;


import br.com.sgps.application.inscricao.InscricaoApplicationService;
import br.com.sgps.application.inscricao.InscricaoInput;
import br.com.sgps.domain.entity.Inscricao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/inscricoes")
@RequiredArgsConstructor
public class InscricaoController {

    private final InscricaoApplicationService inscricaoApplicationService;



    @PostMapping("/inscrever")
    public void realizarInscricao(@RequestBody InscricaoInput inscricao) {
        inscricaoApplicationService.inscreverCandidatoEmVaga(
                UUID.fromString(inscricao.getIdCandidato())
                ,UUID.fromString(inscricao.getIdVaga()));
    }

    @PostMapping("/{idInscricao}/avancar-etapa")
    public void avancarEtapaInscricao(@PathVariable String idInscricao) {
        inscricaoApplicationService.avancarProximaEtapaDaInscricao(
                UUID.fromString(idInscricao));
    }
}
