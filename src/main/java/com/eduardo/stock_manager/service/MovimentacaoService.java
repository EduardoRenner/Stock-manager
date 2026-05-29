package com.eduardo.stock_manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.eduardo.stock_manager.model.Movimentacao;
import com.eduardo.stock_manager.GlobalExceptionHandler.EstoqueInsuficienteException;
import com.eduardo.stock_manager.model.Item;
import com.eduardo.stock_manager.repository.MovimentacaoRepository;


@Service
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    
    @Autowired
    private ItemService itemService;

    @Autowired
    private EmailService emailService;

    public void registrarEntrada(Movimentacao movimentacao, Integer quantidade) {
        Item itemCompleto = itemService.findItemById(movimentacao.getItem().getId());
        itemCompleto.setQuantidade(itemCompleto.getQuantidade() + quantidade);
        itemService.updateItem(itemCompleto);
        movimentacaoRepository.save(movimentacao);
    }

    @Value("${app.email.destinatario}")
    private String destinatarioEmail;
    public void registrarSaida(Movimentacao movimentacao, Integer quantidade) {
        Item itemCompleto = itemService.findItemById(movimentacao.getItem().getId());
        if (itemCompleto.getQuantidade() < quantidade) {
            throw new EstoqueInsuficienteException("Quantidade insuficiente em estoque para o item: " + itemCompleto.getNome());
         } else {
            itemCompleto.setQuantidade(itemCompleto.getQuantidade() - quantidade);
            itemService.updateItem(itemCompleto);
            if (itemCompleto.getQuantidade() < itemCompleto.getEstoqueMinimo()) {
                emailService.enviarAlertaEstoqueMinimo(destinatarioEmail, "Alerta de Estoque Mínimo", "O item " + itemCompleto.getNome() + " está abaixo do estoque mínimo!");
            }
            movimentacaoRepository.save(movimentacao);
         }
    }

    public List<Movimentacao> findAllMovimentacoes(Long itemId) {
        return movimentacaoRepository.findAllById(itemId);
    }
}
