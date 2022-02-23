package com.amestrete.meuBanco;

import com.amestrete.meuBanco.model.Cliente;
import com.amestrete.meuBanco.model.Conta;
import com.amestrete.meuBanco.model.Transferencia;
import com.amestrete.meuBanco.repository.ClienteRepository;
import com.amestrete.meuBanco.repository.ContaRepository;
import com.amestrete.meuBanco.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private ClienteRepository       clienteRepository;
    @Autowired
    private ContaRepository         contaRepository;
    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Override
    public void run(String... args) throws Exception {
        Cliente c1 = new Cliente(1,"Arthur",1);
        Cliente c2 = new Cliente(2,"Kevin",2);
        Cliente c3 = new Cliente(3,"Jorge",3);

        this.clienteRepository.save(c1);
        this.clienteRepository.save(c2);
        this.clienteRepository.save(c3);

        Conta acc1 = new Conta(1,1,5000);
        Conta acc2 = new Conta(2,2,10000);
        Conta acc3 = new Conta(3,3,7500);

        this.contaRepository.save(acc1);
        this.contaRepository.save(acc2);
        this.contaRepository.save(acc3);

        Transferencia t1 = new Transferencia(1,1,2,1,500,LocalDateTime.now(),false);
        Transferencia t2 = new Transferencia(2,2,3,4,400,LocalDateTime.now(),false);
        Transferencia t3 = new Transferencia(3,3,1,2,1250, LocalDateTime.now(), false);

        this.transferenciaRepository.save(t1);
        this.transferenciaRepository.save(t2);
        this.transferenciaRepository.save(t3);
    }
}


