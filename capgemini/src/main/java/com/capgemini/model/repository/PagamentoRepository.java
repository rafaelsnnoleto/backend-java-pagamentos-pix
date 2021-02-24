package com.capgemini.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import com.capgemini.model.entity.PagamentoEntity;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {

    @Query("select sum(p.valor) from pagamento p where p.dataPagamento between :start and :end")
    BigDecimal sumByDataPagamentoBetween(LocalDateTime start, LocalDateTime end);

}