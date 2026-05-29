package com.eduardo.stock_manager.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eduardo.stock_manager.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i WHERE i.isAtivo = true")
    List<Item> findAllAtivos();

    @Query("SELECT i FROM Item i WHERE i.quantidade < i.estoqueMinimo AND i.isAtivo = true")
    List<Item> findItensAtivosAbaixoDoMinimo();
}

