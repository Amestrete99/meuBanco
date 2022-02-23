package com.amestrete.meuBanco.repository;

import com.amestrete.meuBanco.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia,Integer> {

}
