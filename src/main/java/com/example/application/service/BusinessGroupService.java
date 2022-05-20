package com.example.application.service;


import com.example.application.repository.BusinessGroupRepository;
import com.example.application.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessGroupService {

    @Autowired
    BusinessGroupRepository businessGroupRepository;


}
