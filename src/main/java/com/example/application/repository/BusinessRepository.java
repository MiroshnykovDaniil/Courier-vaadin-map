package com.example.application.repository;

import com.example.application.model.Business;
import com.vaadin.flow.component.charts.model.Items;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface BusinessRepository extends CrudRepository<Business,String> {

    List<Business> findBusinessByTitleContains(String str);

    List<Business> findAllByTitle(String str);


}
