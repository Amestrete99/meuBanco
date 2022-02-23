package com.amestrete.meuBanco.Service;


import com.amestrete.meuBanco.model.Cliente;
import com.amestrete.meuBanco.model.Conta;
import com.amestrete.meuBanco.model.Transferencia;
import com.amestrete.meuBanco.repository.ClienteRepository;
import com.amestrete.meuBanco.repository.ContaRepository;
import com.amestrete.meuBanco.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Service responsável pelas operações com Transferencia.
 */
@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ContaService contaService;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Método responsável por criar uma transferência.
     * @param transferencia Contendo os dados da transferência: Id Conta origem e Id Conta destino, Data, Valor, Número de parcelas
     */
    public void criarTransferencia(Transferencia transferencia) {

        List<Conta> contas = contaRepository.findAll();
        List<Cliente> clientes = clienteRepository.findAll();

        Conta contaOrigem = contas
                .stream()
                .filter(conta -> conta.getIdConta().equals(transferencia.getIdContaOrigem()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Conta com id não encontrado"));

        Conta contaDestino = contas
                .stream()
                .filter(conta -> conta.getIdConta().equals(transferencia.getIdContaDestino()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Conta com id não encontrado"));

        if(transferencia.getParcelas()>0 && transferencia.getValor()>0
            && contaOrigem.getSaldo()>=transferencia.getValor()) {


            if(contaOrigem.getSaldo() >= transferencia.getValor()) {
                Transferencia temp = new Transferencia();
                temp.setIdTransferencia(transferencia.getIdTransferencia());
                temp.setIdContaOrigem(transferencia.getIdContaOrigem());
                temp.setIdContaDestino(transferencia.getIdContaDestino());
                temp.setParcelas(transferencia.getParcelas());
                temp.setValor(transferencia.getValor());
                temp.setDataTransferencia(transferencia.getDataTransferencia());
                temp.setRevertida(transferencia.isRevertida());


                double envioContaOrigem = transferencia.getValor();
                double valorOrigem = contaOrigem.getSaldo() - envioContaOrigem;
                double valorDestino = contaDestino.getSaldo() + envioContaOrigem;

                contaOrigem.setSaldo(valorOrigem);
                contaDestino.setSaldo(valorDestino);
                transferenciaRepository.save(temp);
                contaRepository.save(contaOrigem);
                contaRepository.save(contaDestino);
            }
            } else {
                throw new RuntimeException("Valor inválido");
            }

        }






    /**
     * Método responsável por reverter uma transferência.
     * As transferencias revertidas contém uma flag. Para a reversão, trocamos Conta Origem por Conta Destino e vice-versa.
     * @param idTransferencia IdTransferencia da transferência que será revertida.
     */

    public void reverterTransferencia(Integer idTransferencia) {

        List<Transferencia> transferencias = transferenciaRepository.findAll();
        List<Conta> contas = contaRepository.findAll();
        List<Transferencia> transferenciasRevertidas;

       Transferencia transferenciaReverter =  transferencias.stream()
                .filter(transferencia -> transferencia.getIdTransferencia().equals(idTransferencia))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Transferência não encontrada"));

       Conta contaOrigem = contas.stream()
               .filter(conta -> conta.getIdConta().equals(transferenciaReverter.getIdContaOrigem()))
               .findFirst()
               .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        Conta contaDestino = contas.stream()
                .filter(conta -> conta.getIdConta().equals(transferenciaReverter.getIdContaDestino()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));


        double valorRetornar = transferenciaReverter.getValor();
        double valorOrigem = contaOrigem.getSaldo()  + valorRetornar;
        double valorDestino = contaDestino.getSaldo() - valorRetornar;

        contaOrigem.setSaldo(valorOrigem);
        contaDestino.setSaldo(valorDestino);
        transferenciaReverter.setRevertida(true);
        transferenciaRepository.save(transferenciaReverter);
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

    }

    /**
     * Método responsável por criar transferências futuras, dividindo o valor em parcelas.
     * @param transferenciaFutura Contendo dados da transferência: Número de parcelas, Valor e Data.
     */

    @Transactional
    public List<Transferencia> criarTransferenciaFutura(Transferencia transferenciaFutura) {
/**
 * A possibilidade de o cliente fazer fazer transferencias futuras com valores acima do seu saldo bancário
 * gera um saldo negativo, o que foi interpretado como uma dívida.
 *
 */
        List<Transferencia> transferenciasFuturas = transferenciaRepository.findAll();
        List<Conta> contas = contaRepository.findAll();


        Conta contaOrigem = contas.stream()
                .filter(conta -> conta.getIdConta().equals(transferenciaFutura.getIdContaOrigem()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        Conta contaDestino = contas.stream()
                .filter(conta -> conta.getIdConta().equals(transferenciaFutura.getIdContaDestino()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        double valorMensal = (transferenciaFutura.getValor()) / (transferenciaFutura.getParcelas());

        if (contaOrigem.getSaldo() >= valorMensal && transferenciaFutura.getParcelas() >= 0
                && transferenciaFutura.getValor() > 0) {

            for (int i = 0; i < transferenciaFutura.getParcelas(); i++) {

                LocalDateTime dataTransferencia = transferenciaFutura.getDataTransferencia().plusMonths(i + 1);

                Transferencia transferencia = new Transferencia();

                transferencia.setIdContaOrigem(transferenciaFutura.getIdContaOrigem());
                transferencia.setIdContaDestino(transferenciaFutura.getIdContaDestino());
                transferencia.setParcelas(transferenciaFutura.getParcelas());
                transferencia.setValor(valorMensal);
                transferencia.setRevertida(false);
                transferencia.setDataTransferencia(dataTransferencia);

                double saldoContaOrigem = (contaRepository.findById(transferenciaFutura.getIdContaOrigem()).get().getSaldo()) - valorMensal;
                double saldoContaDestino = (contaRepository.findById(transferenciaFutura.getIdContaDestino()).get().getSaldo()) - valorMensal;

                contaOrigem.setSaldo(saldoContaOrigem);
                contaDestino.setSaldo(saldoContaDestino);
                transferenciasFuturas.add(transferenciaFutura);
                transferenciaRepository.save(transferencia);
            }
        } else {
            throw new RuntimeException("Parâmetros inválidos");
        }
        return transferenciasFuturas;


    }
}