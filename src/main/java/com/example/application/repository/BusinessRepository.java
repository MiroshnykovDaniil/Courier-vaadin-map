package com.example.application.repository;

import com.example.application.model.Business;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BusinessRepository extends ReactiveCrudRepository<Business,String> {

    Mono<Business> findBusinessByTitleContains(String str);

    Flux<Business> findAllByTitle(String str);


}
