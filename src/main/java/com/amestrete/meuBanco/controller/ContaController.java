package com.amestrete.meuBanco.controller;


import com.amestrete.meuBanco.model.Conta;
import com.amestrete.meuBanco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    ContaRepository contaRepository;

    @GetMapping
    public List<Conta> consultarContas(){

        return contaRepository.findAll() ;
    }


}
