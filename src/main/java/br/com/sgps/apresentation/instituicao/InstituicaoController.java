package br.com.sgps.apresentation.instituicao;

import br.com.sgps.application.instituicao.InstituicaoInput;
import br.com.sgps.application.instituicao.InstituicaoManagementApplicationService;
import br.com.sgps.application.instituicao.InstituicaoOutPut;
import br.com.sgps.domain.entity.Instituicao;
import br.com.sgps.domain.valueobject.InstituicaoId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/instituicao")
@RequiredArgsConstructor
public class InstituicaoController {

    private final InstituicaoManagementApplicationService instituicaoService;

    @PostMapping
    public InstituicaoOutPut salvar(@RequestBody InstituicaoInput instituicaoInput){
        Instituicao instituicaoSalva =  instituicaoService.criar(instituicaoInput);

        return  new InstituicaoOutPut(instituicaoSalva);
    }

    @PutMapping("/{idInstituicao}")
    public InstituicaoOutPut alterar(@RequestBody InstituicaoInput instituicaoInput, @RequestParam String idInstituicao){
        Instituicao instituicaoSalva =  instituicaoService.alterar(new InstituicaoId(UUID.fromString(idInstituicao)),instituicaoInput);

        return  new InstituicaoOutPut(instituicaoSalva);
    }

    @GetMapping("/{idInstituicao}")
    public InstituicaoOutPut consultarPorId(@RequestParam String id){
        Instituicao instituicao = instituicaoService.conusltarPorID(new InstituicaoId(UUID.fromString(id)));

        return new InstituicaoOutPut(instituicao);
    }


}
