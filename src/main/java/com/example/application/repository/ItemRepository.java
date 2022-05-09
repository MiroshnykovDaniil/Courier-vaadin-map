package com.example.application.repository;


import com.example.application.model.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends ReactiveCrudRepository<Item,String> {

}
