package com.amestrete.meuBanco.controller;

import com.amestrete.meuBanco.Service.ContaService;
import com.amestrete.meuBanco.Service.TransferenciaService;
import com.amestrete.meuBanco.model.Cliente;
import com.amestrete.meuBanco.repository.ClienteRepository;
import com.amestrete.meuBanco.repository.ContaRepository;
import com.amestrete.meuBanco.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    TransferenciaRepository transferenciaRepository;

    @Autowired
    ContaService contaService;

    @Autowired
    TransferenciaService transferenciaService;

    @GetMapping
    public List<Cliente> consultarClientes(){

        return clienteRepository.findAll() ;
    }

    @GetMapping(path="/conta/{idConta}")
    public Double consultarSaldo(@PathVariable("idConta") Integer idConta) {
        return contaService.consultaSaldo(idConta);

    }

}

