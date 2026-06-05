package com.rafasantosdevv.sysMarcial.controller;

import com.rafasantosdevv.sysMarcial.dto.AlunoRequest;
import com.rafasantosdevv.sysMarcial.dto.AlunoResponse;
import com.rafasantosdevv.sysMarcial.service.AlunoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    public final AlunoService alunoService;

    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoResponse cadastrar(@RequestBody AlunoRequest alunoRequest){
        return alunoService.cadastrarAluno(alunoRequest);
    }

    public Page<AlunoResponse> listar(Pageable pageable){
        return alunoService.listarAluno(pageable);
    }

    @GetMapping("/{id}")
    public AlunoResponse buscarPorId(@PathVariable long id){
        return alunoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public AlunoResponse atualizar(@PathVariable long id, @RequestBody AlunoRequest alunoRequest){
        return alunoService.atualizarAluno(id, alunoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable long id){
        alunoService.excluirAluno(id);
    }
}
