package com.amestrete.meuBanco.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer       idTransferencia;
    private Integer       idContaOrigem;
    private Integer       idContaDestino;
    private Integer        parcelas;
    private double         valor;
    private LocalDateTime  dataTransferencia;
    boolean revertida =    false;

    public Transferencia(Integer idTransferencia, Integer idContaOrigem, Integer idContaDestino, int parcelas, double valor, LocalDateTime dataTransferencia, boolean revertida) {
        this.idTransferencia   = idTransferencia;
        this.idContaOrigem     = idContaOrigem;
        this.idContaDestino    = idContaDestino;
        this.parcelas          = parcelas;
        this.valor             = valor;
        this.dataTransferencia = dataTransferencia;
        this.revertida         = revertida;
    }
}
