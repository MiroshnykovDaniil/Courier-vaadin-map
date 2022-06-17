package com.example.application.repository;

import com.example.application.model.Marker;
import org.springframework.data.repository.CrudRepository;

public interface MarkerRepository extends CrudRepository<Marker, String> {
}