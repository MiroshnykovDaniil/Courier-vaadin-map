package com.example.application.repository;


import com.example.application.model.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends ReactiveCrudRepository<Item,String> {

}
