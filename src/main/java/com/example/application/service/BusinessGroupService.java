package com.example.application.service;


import com.example.application.model.Business;
import com.example.application.model.BusinessGroup;
import com.example.application.model.Item;
import com.example.application.repository.BusinessGroupRepository;
import com.example.application.repository.BusinessRepository;
import com.vaadin.flow.component.charts.model.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class BusinessGroupService {

    private static Logger logger = LoggerFactory.getLogger(BusinessGroupService.class);

    @Autowired
    BusinessGroupRepository businessGroupRepository;
    @Autowired
    BusinessService businessService;
    @Autowired
    ItemsService itemsService;


    public BusinessGroup createBusinessGroup(String title){
        BusinessGroup businessGroup = new BusinessGroup();
        businessGroup.setTitle(title);
        return businessGroupRepository.save(businessGroup);
    }


    public void assignBusinesses(BusinessGroup businessGroup,Set<Business> businesses){
        validateBusinessGroup(businessGroup);
        for(Business business : businesses){
            businessService.validateBusiness(business);
        }
        businessGroup.setBusinesses(businesses);
        businessGroupRepository.save(businessGroup);
    }

    public void assignDefaultItems(BusinessGroup businessGroup,Set<Item> items){
        validateBusinessGroup(businessGroup);
        items.forEach(item -> itemsService.validateItem(item));
        Set<Business> businesses = businessGroup.getBusinesses();
        businesses.forEach(business -> {
            System.out.println("BUSINESSID:"+business.getId());
            businessService.validateBusiness(business);
            business.setItems(items);
            businessService.businessRepository.save(business);
           // catch (DataIntegrityViolationException e){System.out.println("Entity already"+ e.getLocalizedMessage()+" exists. Skipping ...");}
        });
    }

    public void validateBusinessGroup(BusinessGroup businessGroup){
        businessGroupRepository.findById(businessGroup.getId()).orElseThrow(()->new NoSuchElementException("BusinessGroup nto found"));
    }


}
