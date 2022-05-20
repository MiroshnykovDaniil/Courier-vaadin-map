package com.example.application.repository;

import com.example.application.model.BusinessGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessGroupRepository extends JpaRepository<BusinessGroup, String> {
}
