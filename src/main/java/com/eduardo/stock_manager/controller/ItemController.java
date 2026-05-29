package com.eduardo.stock_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.stock_manager.dto.ItemRequestDTO;
import com.eduardo.stock_manager.dto.ItemResponseDTO;
import com.eduardo.stock_manager.model.Item;
import com.eduardo.stock_manager.service.ItemService;



@RestController
@RequestMapping("/itens")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    public ItemResponseDTO createItem(@RequestBody ItemRequestDTO item) {
        Item novoItem = new Item();
        novoItem.setNome(item.nome());
        novoItem.setMarca(item.marca());
        novoItem.setDataValidade(item.dataValidade());
        novoItem.setQuantidade(item.quantidade());
        novoItem.setEstoqueMinimo(item.estoqueMinimo());
        Item criadoItem = itemService.createItem(novoItem);
        return new ItemResponseDTO(criadoItem.getId(), criadoItem.getNome(), criadoItem.getMarca(), criadoItem.getDataValidade(), criadoItem.getQuantidade(), criadoItem.getEstoqueMinimo(), criadoItem.getIsAtivo());
    }

    @GetMapping("/{id}")
    public ItemResponseDTO findItemById(@PathVariable Long id) {
        Item item = itemService.findItemById(id);
        return new ItemResponseDTO(item.getId(), item.getNome(), item.getMarca(), item.getDataValidade(), item.getQuantidade(), item.getEstoqueMinimo(), item.getIsAtivo());
    }

    @GetMapping
    public List<ItemResponseDTO> findAllItems() {
         List<Item> itens = itemService.findAllItems();
        return itens.stream().map(item -> new ItemResponseDTO(item.getId(), item.getNome(), item.getMarca(), item.getDataValidade(), item.getQuantidade(), item.getEstoqueMinimo(), item.getIsAtivo())).toList();
    }

    @PutMapping("/{id}")
    public ItemResponseDTO updateItem(@PathVariable Long id, @RequestBody ItemRequestDTO item) {
        Item itemToUpdate = itemService.findItemById(id);
        itemToUpdate.setNome(item.nome());
        itemToUpdate.setMarca(item.marca());
        itemToUpdate.setDataValidade(item.dataValidade());
        itemToUpdate.setQuantidade(item.quantidade());
        itemToUpdate.setEstoqueMinimo(item.estoqueMinimo());
        Item updatedItem = itemService.updateItem(itemToUpdate);
        return new ItemResponseDTO(updatedItem.getId(), updatedItem.getNome(), updatedItem.getMarca(), updatedItem.getDataValidade(), updatedItem.getQuantidade(), updatedItem.getEstoqueMinimo(), updatedItem.getIsAtivo());
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

    @GetMapping("/relatorio")
    public List<ItemResponseDTO> findItensAbaixoDoMinimo(){
        return itemService.findItensAbaixoDoMinimo().stream().map(item -> new ItemResponseDTO(item.getId(), item.getNome(), item.getMarca(), item.getDataValidade(), item.getQuantidade(), item.getEstoqueMinimo(), item.getIsAtivo())).toList();
    }
}
