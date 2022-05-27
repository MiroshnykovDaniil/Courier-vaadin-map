package com.example.application.service;


import com.example.application.model.Item;
import com.example.application.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ItemsService {

    @Autowired
    private ItemRepository itemRepository;


    public void validateItem(Item item){
        itemRepository.findById(item.getId()).orElseThrow(()-> new NoSuchElementException("Item with id "+item.getId()+" not found"));
    }
}
