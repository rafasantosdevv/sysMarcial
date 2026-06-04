package com.rafasantosdevv.sysMarcial.domain;


import com.rafasantosdevv.sysMarcial.domain.enums.StatusFatura;
import com.rafasantosdevv.sysMarcial.domain.enums.StatusMatricula;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "faturas_matriculas")
public class FaturaMatricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula_id")
    private Matricula matricula;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    private BigDecimal valor;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    @Column(name = "data_cancelamento")
    private LocalDateTime dataCancelamento;

    @Enumerated(EnumType.STRING)
    private StatusFatura status = StatusFatura.ABERTA;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public LocalDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public StatusFatura getStatus() {
        return status;
    }

    public void setStatus(StatusFatura status) {
        this.status = status;
    }
    /*
    matricula_id BIGINT NOT NULL REFERENCES matriculas(id),
    data_vencimento DATE NOT NULL,
    valor NUMERIC(10, 2) NOT NULL CHECK ( valor >= 0 ),
    data_pagamento TIMESTAMP,
    data_cancelamento DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'ABERTA',
    CHECK ( status IN ('ABERTA', 'PAGA', 'CANCELADA', 'VENCIDA') ),
    UNIQUE (matricula_id, data_vencimento) */
}
