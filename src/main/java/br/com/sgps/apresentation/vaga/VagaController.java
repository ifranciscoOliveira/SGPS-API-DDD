package br.com.sgps.apresentation.vaga;

import br.com.sgps.application.vaga.*;
import br.com.sgps.domain.valueobject.VagaId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vaga")
@RequiredArgsConstructor
public class VagaController {

    private final VagaApplicationService vagaApplicationService;
    private final VagaAssembler vagaAssembler;

    @GetMapping
    public List<VagaOutPut> consultarTodos(){
        return vagaApplicationService.consultarTodos()
                .stream()
                .map(vagaAssembler::domainToOutPut
                )
                .toList();
    }

    @PostMapping
    public VagaOutPut salvar(@RequestBody VagaInput vagaInput){
        return vagaAssembler.domainToOutPut(vagaApplicationService.criar(vagaInput));

    }

    @PutMapping
    public VagaOutPut alterar(@RequestBody VagaAlterarInput vagaAlterarInput, @PathVariable String id){
        return vagaAssembler.domainToOutPut(vagaApplicationService.alterar(new VagaId(UUID.fromString(id)),vagaAlterarInput));
    }
}
