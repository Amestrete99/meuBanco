package com.amestrete.meuBanco.Service;



import com.amestrete.meuBanco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service responsável pelas operações com Conta.
 */
@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;



    /**
     * Método responsável pela consulta de saldo de uma Conta através do id.
     *
     * @param idConta identificador da conta que será consultada
     * @return Saldo da conta, caso encontrada
     */

    //public Double getSaldoById(Integer idConta) {
    //  verificaContaExiste(id);

    //  return movimentacaoService.getSomatorioByConta(id);
    // }

    /**
     * Método responsável por consultar uma Conta usando os dados Banco, Agência, Número e Digito.
     *
     * @param contaDto Objeto com os dados da conta que será usado na consulta.
     * @return Conta encontrada caso exista. Será criada uma exceção caso não encontre.
     */
    //public ContaRetornoDto getBy(ContaDto contaDto) {
    //   Banco banco = bancoService.getById(contaDto.getIdBanco());
    //   Optional<Conta> contaOptional = contaRepository.findByAgenciaAndNumeroAndDigitoAndBanco(contaDto.getAgencia(), contaDto.getNumero(), contaDto.getDigito(), banco);

    //   if (contaOptional.isEmpty())
    //      return null;

    //   return ContaRetornoDto.valueOf(contaOptional.get());
    // }

    /**
     * Método responsável por consultar as transferências futuras de uma Conta.
     *
     * @param id Id da Conta que será usada na consulta.
     * @return Lista contendo as transferências futuras da conta.
     */
    //public List<Transferencia> getTransferenciasById(Integer id) {

       // return transferenciaService.getFuturasByConta(id);
     //}

    /**
     * Método responsável por consultar as transferências executadas de uma Conta.
     *
     * @param id Id da Conta que será usada na consulta.
     * @return Lista contendo as transferências executadas da conta.
     */
    //public List<TransferenciaRetornoDto> getTransferenciasById(long id) {
    //    verificaContaExiste(id);

    //    return transferenciaService.getByConta(id);
    //}

    /**
     * Método responsável por verificar se uma conta existe.
     *
     * @param idConta Id que será verificado a existência. Caso não exista irá disparar a exceção ContaNaoEncontradaException.
     */
    public double consultaSaldo(Integer idConta) {

        double saldo = contaRepository.findById(idConta).stream().findFirst().get().getSaldo();
        return saldo;

    }

}
