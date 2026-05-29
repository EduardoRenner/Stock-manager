package com.eduardo.stock_manager.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eduardo.stock_manager.model.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    @Query("SELECT m FROM Movimentacao m WHERE m.item.id = :itemId")
    List<Movimentacao> findAllById(@Param("itemId") Long itemId);
}