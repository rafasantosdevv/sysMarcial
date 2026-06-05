package com.rafasantosdevv.sysMarcial.dto;

import com.rafasantosdevv.sysMarcial.domain.Aluno;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AlunoRequest(
        @NotBlank(message = "O Nome é obrigatório")
        @Size(max = 150, message = "O Nome pode ter no máximo 150 carácteres")
        String nome,

        @Past(message = "A data deve estar no passado")
        LocalDate dataNascimento,

        @Size(max =1, message = "Defina com apenas M(Masculino) ou F(Feminino)")
        String sexo,

        @Size(max = 30, message = "No máximo 30 numeros para telefone")
        String telefone,

        @Size(max = 30, message = "No máximo 30 numeros para celular")
        String celular,

        @Email(message = "Email inválido.")
        @Size(max = 140, message = "No máximo 140 caracteres")
        String email,

        String observacao,

        @Size(max = 150, message = "No máximo 150 carácteres para o endereço")
        String endereco,

        @Size(max = 5, message = "No máximo 5 carácteres para o número da residência")
        String numero,

        @Size(max = 150, message = "No máximo 150 carácteres para o complemento")
        String complemento,

        @Size(max = 130, message = "No máximo 130 carácteres para o bairro")
        String bairro,

        @Size(max = 130, message = "No máximo 130 carácteres para a cidade")
        String cidade,

        @Size(max = 3, message = "No máximo 3 carácteres para o estado")
        String estado,

        @Size(max = 20, message = "No máximo 10 carácteres para o cep")
        String cep
) {
    public Aluno toEntity(){
        Aluno aluno = new Aluno();
        preencher(aluno);
        return aluno;
    }
    public void preencher(Aluno aluno){
        aluno.setNome(nome);
        aluno.setDataNascimento(dataNascimento);
        aluno.setSexo(sexo);
        aluno.setTelefone(telefone);
        aluno.setCelular(celular);
        aluno.setEmail(email);
        aluno.setObservacao(observacao);
        aluno.setEndereco(endereco);
        aluno.setNumero(numero);
        aluno.setComplemento(complemento);
        aluno.setBairro(bairro);
        aluno.setCidade(cidade);
        aluno.setEstado(estado);
        aluno.setCep(cep);
    }
}
