package com.rafasantosdevv.sysMarcial.service;

import com.rafasantosdevv.sysMarcial.domain.Aluno;
import com.rafasantosdevv.sysMarcial.dto.AlunoRequest;
import com.rafasantosdevv.sysMarcial.dto.AlunoResponse;
import com.rafasantosdevv.sysMarcial.repository.AlunoRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.awt.print.*;

@Service
public class AlunoService {

    public final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoResponse cadastrarAluno(AlunoRequest request){
        if (request.email() != null && alunoRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Já existe um aluno com esse email");
        }
        Aluno aluno = request.toEntity();
        Aluno alunoSalvo = alunoRepository.save(aluno);
        return AlunoResponse.fromEntity(alunoSalvo);
    }

    public Page<AlunoResponse> listar(Pageable pageable){
        return alunoRepository.findAll(pageable).map(AlunoResponse::fromEntity);
    }

    public AlunoResponse buscarPorId(long id){
        Aluno aluno = buscarEntidadePorId(id);
        return AlunoResponse.fromEntity(aluno);
    }

    public AlunoResponse atualizar(long id, AlunoRequest request){
        Aluno aluno = buscarEntidadePorId(id);
        request.preencher(aluno);
        Aluno alunoAtualizado = alunoRepository.save(aluno);
        return AlunoResponse.fromEntity(alunoAtualizado);
    }

    public void remover(long id){
        Aluno aluno = buscarEntidadePorId(id);
        alunoRepository.delete(aluno);
    }

    public Aluno buscarEntidadePorId(long id){
        return alunoRepository.findById(id).orElseThrow(()-> new RuntimeException("Esse aluno não existe"));
    }
}
