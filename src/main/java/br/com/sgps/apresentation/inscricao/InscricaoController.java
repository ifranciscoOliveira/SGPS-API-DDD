package br.com.sgps.apresentation.inscricao;


import br.com.sgps.application.inscricao.InscricaoApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/inscricoes")
@RequiredArgsConstructor
public class InscricaoController {

    private final InscricaoApplicationService inscricaoApplicationService;



    @PostMapping("inscrever")
    public void realizarInscricao(@PathVariable String idCandidato
            , @PathVariable String idVaga) {
        inscricaoApplicationService.inscreverCandidatoEmVaga(
                UUID.fromString(idCandidato)
                ,UUID.fromString(idVaga));
    }

    @PostMapping("/{idInscricao}/avancar-etapa")
    public void avancarEtapaInscricao(@PathVariable String idInscricao) {
        inscricaoApplicationService.avancarProximaEtapaDaInscricao(
                UUID.fromString(idInscricao));
    }
}
