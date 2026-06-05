package com.rafasantosdevv.sysMarcial.specification;

import com.rafasantosdevv.sysMarcial.domain.Aluno;
import com.rafasantosdevv.sysMarcial.dto.AlunoFiltroRequest;
import org.springframework.data.jpa.domain.Specification;

public class AlunoSpecification {

    public static Specification<Aluno> comFiltro(AlunoFiltroRequest filtro){
        return Specification
                .where(nomeContem(filtro.nome()))
                .and(emailContem(filtro.email()))
                .and(celularContem(filtro.celular()))
                .and(cidadeContem(filtro.cidade()))
                .and(estadoIgual(filtro.estado()));

    }

    private static Specification<Aluno> estadoIgual(String estado) {
        return (root, query, criteriaBuilder) -> {
            if (estado == null || estado.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("estado"), "%" + estado.toUpperCase() + "%");
        };
    }

    private static Specification<Aluno> cidadeContem(String cidade) {
        return (root, query, criteriaBuilder) -> {
            if (cidade == null || cidade.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("cidade"), "%" + cidade.toLowerCase() + "%");
        };
    }

    private static Specification<Aluno> celularContem(String celular) {
        return (root, query, criteriaBuilder) -> {
            if (celular == null || celular.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("celular"), "%" + celular + "%");
        };
    }

    private static Specification<Aluno> emailContem(String email) {
        return (root, query, criteriaBuilder) -> {
            if (email == null || email.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("email"), "%" + email.toLowerCase() + "%");
        };
    }

    private static Specification<Aluno> nomeContem(String nome) {
        return (root, query, criteriaBuilder) -> {
            if (nome == null || nome.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("nome"), "%" + nome.toLowerCase() + "%");
        };
    }
}
