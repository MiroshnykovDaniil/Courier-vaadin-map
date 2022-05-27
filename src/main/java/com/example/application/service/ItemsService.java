package com.example.application.service;


import com.example.application.model.Item;
import com.example.application.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
public class ItemsService {

    @Autowired
    private ItemRepository itemRepository;


    public void validateItem(Item item){
        itemRepository.findById(item.getId()).orElseThrow(()-> new NoSuchElementException("Item with id "+item.getId()+" not found"));
    }

    public Item createItem(String title, BigDecimal price){
        Item item = new Item();
        item.setPrice(price);
        item.setTitle(title);
        return itemRepository.save(item);
    }
}
