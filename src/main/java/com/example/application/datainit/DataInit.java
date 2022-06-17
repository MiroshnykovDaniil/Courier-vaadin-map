package com.example.application.datainit;

import com.example.application.model.Business;
import com.example.application.model.BusinessGroup;
import com.example.application.model.Item;
import com.example.application.model.Marker;
import com.example.application.service.BusinessGroupService;
import com.example.application.service.BusinessService;
import com.example.application.service.ItemsService;
import com.example.application.service.MarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInit {

    @Autowired
    ItemsService itemsService;
    @Autowired
    BusinessGroupService businessGroupService;
    @Autowired
    BusinessService businessService;
    @Autowired
    MarkerService markerService;


    @PostConstruct
    public void initData(){
        Set<Item> macItems =  initMacItems();
        Set<Business> macBusinesses = initMacs();
        BusinessGroup businessGroup = initMacGroup(macBusinesses,macItems);
        initMacIcon(businessGroup,"mac");
    }

    private Set<Item> initMacItems(){
        Set<Item> items = new HashSet<>();

        items.add(itemsService.createItem("Mac Burger",BigDecimal.valueOf(100)));
        items.add(itemsService.createItem("Mac IceCream",BigDecimal.valueOf(50)));
        items.add(itemsService.createItem("Mac Coffee",BigDecimal.valueOf(40)));
        return items;

    }

    private Set<Business> initMacs(){
        Set<Business> businesses = new HashSet<>();
        businesses.add(businessService.createBusiness("Mac univer","univer",4033717.7887060153,6447190.040808506));
        businesses.add(businessService.createBusiness("Mac univer 2","univer 2",4033717.7887260153,6447190.040828506));
        return businesses;
    }

    private BusinessGroup initMacGroup(Set<Business> businesses, Set<Item> items){
        BusinessGroup businessGroup = businessGroupService.createBusinessGroup("Mac");
        businessGroupService.assignBusinesses(businessGroup,businesses);
        businessGroupService.assignDefaultItems(businessGroup,items);
        return businessGroup;
    }

    private void initMacIcon(BusinessGroup group, String img){
        markerService.createMarker(group,img);
    }
}
