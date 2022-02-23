Aplicação simulando operações bancarias para teste no processo seletivo da empresa MeuTudo.

<h2>Tecnologias e ferramentas</h2>
  
<ul>
<li>Java</li> 
<li>Maven Project</li> 
 <li>Spring Boot</li>
<li>Lombok</li>
<li>H2Database</li>
</ul>

<hr>

<h2>Como iniciar</h2>

  Ao executar o programa, a partir do Main localizado em src/main/java/com/amestrete/meuBanco/MeuBancoApplication.java, a aplicação
será iniciada e a base de dados H2 criada.
  Visando a validação das funcionalidades e testes, o banco foi inicilizado com registros de Conta,Transferencia e Cliente.


<hr>

<h2>Registros pre-cadastrados para teste</h2>

contaA { "idConta": 4, " dCliente": 1, "saldo": 3668.0}<br>
contaB { "idConta": 5, "idCliente": 2, "saldo": 10000.0}<br>
contaC { "idConta": 6, "idCliente": 3, "saldo": 7500.0}<br>

clienteA  {"idCliente": 1,"nome": "Arthur","idConta":  1 }<br>
clienteB  {"idCliente": 2,"nome": "Kevin", "idConta":  2 }<br>
clienteC  {"idCliente": 3,"nome": "Jorge", "idConta":  3}<Br>
  
transferenciaA {"idTransferencia": 7, "idContaOrigem": 1, "idContaDestino": 2, "parcelas": 1,
                "dataTransferencia": "2022-04-23T02:00:49.268376" "valor": 500.0, "revertida": false}

transferenciaB {"idTransferencia": 8, "idContaOrigem": 2, "idContaDestino": 3, "parcelas": 4, 
                "dataTransferencia": "2022-04-23T02:00:49.268376""valor": 400.0, , "revertida": false}
  
transferenciaC  {"idTransferencia":9, "idContaOrigem": 3, "idContaDestino": 1, "parcelas": 2, 
                "dataTransferencia": "2022-04-23T02:00:49.268376""valor": 1250.0,"revertida":  false}

<hr>
License
Copyright © 2022, Arthur Amestrete
