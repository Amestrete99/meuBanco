package com.amestrete.meuBanco.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Cliente {

    @Id
    private Integer    idCliente;
    private String     nome;
    private Integer    idConta;

    public Cliente(int idCliente,String nome,int idConta) {
        this.idCliente = idCliente;
        this.nome      = nome;
        this.idConta   = idConta;

    }
}