package com.example.application.repository;

import com.example.application.model.Business;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BusinessRepository extends ReactiveCrudRepository<Business,String> {

    Mono<Business> findBusinessByTitleContains(String str);

    Flux<Business> findAllByTitle(String str);


}
