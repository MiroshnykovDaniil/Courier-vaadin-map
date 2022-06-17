package com.example.application.repository;


import com.example.application.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepository extends CrudRepository<Item,String> {

    @Query(value = "select * FROM item i join business_items b on b.items_id = i.id where b.business_id = :id",nativeQuery = true)
    List<Item> findAllByBusinessId(@Param("id") String id);
}
