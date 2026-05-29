package com.eduardo.stock_manager.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardo.stock_manager.GlobalExceptionHandler.ItemNotFoundException;
import com.eduardo.stock_manager.model.Item;
import com.eduardo.stock_manager.repository.ItemRepository;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item findItemById(Long id) {
        Item item = itemRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException("Item não encontrado com id: " + id));

        if (!item.getIsAtivo()) {
            throw new RuntimeException("Item com id: " + id + " está inativo.");
        }
        return item;
    }

    public List<Item> findAllItems() {
        return itemRepository.findAllAtivos();
    }

    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException("Item não encontrado com id: " + id));
        item.setIsAtivo(false);
        itemRepository.save(item);
        
    }

    public List<Item> findItensAbaixoDoMinimo() {
        return itemRepository.findItensAtivosAbaixoDoMinimo();
    }
}
