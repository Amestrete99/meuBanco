package com.amestrete.meuBanco.controller;

import com.amestrete.meuBanco.Service.ContaService;
import com.amestrete.meuBanco.Service.TransferenciaService;
import com.amestrete.meuBanco.model.Transferencia;
import com.amestrete.meuBanco.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    @Autowired
    TransferenciaService transferenciaService;

    @Autowired
    TransferenciaRepository transferenciaRepository;

    @Autowired
    ContaService contaService;

    @GetMapping
     public List<Transferencia> consultarTransferencias(){
        return transferenciaRepository.findAll() ;
     }

    @PostMapping(path = "/realizarTransferencia")
    public ResponseEntity efetuarTransferencia(@RequestBody @Valid Transferencia transferencia) {
        transferenciaService.criarTransferencia(transferencia);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping(path = "/reverterTransferencia/{idTransferencia}")
    public ResponseEntity reverterTransferencia(@RequestBody @PathVariable("idTransferencia") Integer idTransferencia) {
        transferenciaService.reverterTransferencia(idTransferencia);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping(path = "/transferenciaFutura")
    public ResponseEntity transferenciaFutura(@RequestBody @Valid Transferencia transferencia){
        transferenciaService.criarTransferenciaFutura(transferencia);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
