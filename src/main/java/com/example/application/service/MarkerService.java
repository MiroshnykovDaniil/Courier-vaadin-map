package com.example.application.service;

import com.example.application.model.BusinessGroup;
import com.example.application.model.Marker;
import com.example.application.repository.MarkerRepository;
import com.vaadin.flow.component.map.configuration.style.Icon;
import com.vaadin.flow.server.StreamResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.map.configuration.style.Icon.Options;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MarkerService {
    private static Logger logger = LoggerFactory.getLogger(BusinessGroupService.class);
    @Autowired
    private MarkerRepository markerRepository;

    public List<Marker> getAll(){
        List<Marker> markerList = new ArrayList<>();
        markerRepository.findAll().forEach(markerList::add);
        return markerList;
    }

    public Marker createMarker(BusinessGroup businessGroup, String img){
        Marker marker = new Marker();
        marker.setBusinessGroup(businessGroup);
        marker.setIcon(img);
        return markerRepository.save(marker);
    }

    public Icon setupIcon(Marker marker){
        Options iconOptions = new Options();
        StreamResource streamResource = new StreamResource("",
                () -> getClass().getResourceAsStream("/META-INF/resources/images/"+marker.getIcon()+".png"));
        iconOptions.setImg(streamResource);
        iconOptions.setImgSize(new com.vaadin.flow.component.map.configuration.style.Icon.ImageSize(100,100));
        //macIconOptions.setScale(0.3f);

        Icon icon = new Icon(iconOptions);
        return icon;
    }

}
