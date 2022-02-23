package com.amestrete.meuBanco.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Conta {

    @Id
    private Integer     idConta;
    private Integer     idCliente;
    private double      saldo;

    public Conta(int idConta, int idCliente, double saldo) {
        this.idConta = idConta;
        this.idCliente = idCliente;
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }
}