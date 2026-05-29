package com.eduardo.stock_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.stock_manager.model.Movimentacao;
import com.eduardo.stock_manager.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {
    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping("/entrada")
    public void registrarEntrada(@RequestBody Movimentacao movimentacao) {
        movimentacaoService.registrarEntrada(movimentacao, movimentacao.getQuantidade());
    }

    @PostMapping("/saida")
    public void registrarSaida(@RequestBody Movimentacao movimentacao) {
        movimentacaoService.registrarSaida(movimentacao, movimentacao.getQuantidade());
    }

    @GetMapping("/{itemId}")
    public List<Movimentacao> findAllMovimentacoesById(@PathVariable Long itemId) {
        return movimentacaoService.findAllMovimentacoes(itemId);
    }
}
