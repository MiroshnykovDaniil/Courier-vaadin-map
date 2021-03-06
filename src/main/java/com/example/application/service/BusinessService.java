package com.example.application.service;

import com.example.application.model.Business;
import com.example.application.model.Item;
import com.example.application.repository.BusinessRepository;
import com.example.application.repository.ItemRepository;
import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.map.configuration.Coordinate;
import com.vaadin.flow.component.map.configuration.feature.MarkerFeature;
import com.vaadin.flow.component.map.configuration.style.Icon;
import com.vaadin.flow.server.StreamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class BusinessService {

    @Autowired
    BusinessRepository businessRepository;
    @Autowired
    ItemRepository itemRepository;

    HashMap<Business, MarkerFeature> businessFeatureHashMap = new HashMap<>();

    public List<Item> findAllByBusinessId(Business business){
        return itemRepository.findAllByBusinessId(business.getId());
    }

    public Business createBusiness(String title, String address, double x, double y){
        Coordinate coordinate = new Coordinate(x,y);
        Business.BusinessBuilder builder = new Business.BusinessBuilder();
        builder.setTitle(title);
        builder.setCoordinate(coordinate);
        builder.setAddress(address);
        return businessRepository.save(builder.build());
    }

    public void addItem(Business business, Item item){
        validateBusiness(business);
        item = itemRepository.findById(item.getId()).orElseThrow(()-> new NoSuchElementException("Item not found"));

        business.addToMenu(item);
        businessRepository.save(business);
    }


    public void initData(){
        //example of data for future refactoring
        Coordinate MacDonaldsCoordinates  = new Coordinate(4033717.7887060153,6447190.040808506);

        Business.BusinessBuilder builder = new Business.BusinessBuilder();
        builder.setCoordinate(MacDonaldsCoordinates);
        builder.setTitle("Mac ???? ??????????????");
        builder.setAddress("?????????? ??????????????????????");
        Set<Item> items = new HashSet<>();
        Item fry = new Item();
        fry.setTitle("FRY");
        fry.setId("id");
        fry.setPrice(BigDecimal.valueOf(30));
        items.add(fry);
        builder.setMenu(items);

        Icon.Options macIconOptions = new Icon.Options();
        StreamResource streamResource = new StreamResource("us-flag.png",
                () -> getClass().getResourceAsStream("/META-INF/resources/images/mac.png"));
        macIconOptions.setImg(streamResource);
        macIconOptions.setImgSize(new Icon.ImageSize(100,100));
        //macIconOptions.setScale(0.3f);

        Icon MacIcon = new Icon(macIconOptions);
        //MacIcon.setScale(0.3f);

        businessFeatureHashMap.put(builder.build(),new MarkerFeature(MacDonaldsCoordinates,MacIcon));

        Coordinate BufetCoordinates = new Coordinate(4033789.0296170535, 6447312.519258347);
        Icon.Options BufetIconOptions = new Icon.Options();
        BufetIconOptions.setImgSize(new Icon.ImageSize(100,100));
        streamResource = new StreamResource("us-flag.png",
                () -> getClass().getResourceAsStream("/META-INF/resources/images/bufet.png"));
        BufetIconOptions.setImg(streamResource);
        Icon BufetIcon = new Icon(BufetIconOptions);

        builder.setCoordinate(BufetCoordinates);
        builder.setTitle("Bufet");
        items = new HashSet<>();
        Item pizza = new Item();
        pizza.setTitle("pizza");
        pizza.setId("id");
        pizza.setPrice(BigDecimal.valueOf(30));
        items.add(pizza);
        builder.setMenu(items);

        businessFeatureHashMap.put(builder.build(),new MarkerFeature(BufetCoordinates,BufetIcon));

    }

    public void addIcons(Map map){
        businessFeatureHashMap.forEach((business, feature) -> {
            map.getFeatureLayer().addFeature(feature);
        });
    }

    public void validateBusiness(Business business){
        businessRepository.findById(business.getId()).orElseThrow(()-> new NoSuchElementException("Business not found"));
    }


}
