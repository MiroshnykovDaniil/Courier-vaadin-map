package com.example.application.views;

import com.example.application.model.Business;
import com.example.application.model.BusinessGroup;
import com.example.application.model.Item;
import com.example.application.model.Marker;
import com.example.application.service.BusinessGroupService;
import com.example.application.service.BusinessService;
import com.example.application.service.MarkerService;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.map.configuration.Coordinate;
import com.vaadin.flow.component.map.configuration.View;
import com.vaadin.flow.component.map.configuration.feature.MarkerFeature;
import com.vaadin.flow.component.map.configuration.layer.TileLayer;
import com.vaadin.flow.component.map.configuration.source.OSMSource;
import com.vaadin.flow.component.map.configuration.style.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@SpringComponent
@Component
public class MapView extends VerticalLayout {


    HashMap<MarkerFeature, Business> businessLookup = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(BusinessGroupService.class);

    Map map = new Map();

    public MapView(@Autowired BusinessGroupService businessGroupService, @Autowired MarkerService markerService,
                   @Autowired BusinessService businessService){
        this.setHeightFull();
        this.setPadding(false);
        this.setSpacing(false);
        this.setWidth("1200px");
        this.setClassName("business-view");
        this.getStyle()
                .set("flexShrink", "0")
                .set("background-color", "#FFFCF9")
                .set("text-align", "center");
        this.businessGroupService = businessGroupService;
        this.markerService = markerService;
        this.businessService = businessService;

        init();

    }
    BusinessGroupService businessGroupService;
    MarkerService markerService;
    BusinessService businessService;

    @PostConstruct
    public void init(){
        this.add(map);
        setupOsmSource(map);
        addIcons(map);
    }

    public void addIcons(Map map) {



        BusinessService service = new BusinessService();
        service.initData();
        service.addIcons(map);

        List<Marker> markerList = markerService.getAll();
        markerList.forEach(marker -> {
            BusinessGroup businessGroup = marker.getBusinessGroup();
            Icon icon = markerService.setupIcon(marker);
            businessGroup.getBusinesses().forEach(business -> {
                Coordinate coordinate = business.getCoordinate();
                MarkerFeature markerFeature = new MarkerFeature(coordinate,icon);
                businessLookup.put(markerFeature,business);
                map.getFeatureLayer().addFeature(markerFeature);
            });
        });
//


        map.addFeatureClickListener(e -> {
            Dialog dialog = new Dialog();
            VerticalLayout dialogLayout = createDialogLayout(businessLookup.get(e.getFeature()));
            dialog.add(dialogLayout);


            dialog.add();
            dialog.open();
        });
    }

    private VerticalLayout createDialogLayout(Business business) {
        Label title = new Label("Menu:");
        List<Item> items = businessService.findAllByBusinessId(business);
        Grid<Item> menu = new Grid();
        menu.setSelectionMode(Grid.SelectionMode.MULTI);
        menu.addColumn(Item::getTitle).setHeader("Title").setAutoWidth(true);
        menu.addColumn(Item::getPrice).setHeader("Price").setAutoWidth(true);
        menu.setItems(items);
        items.forEach(item -> {
            //menu.add(new TextField(item.getTitle()+":"+item.getPrice()));
        });

        VerticalLayout dialogLayout = new VerticalLayout(title,
                menu);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

        return dialogLayout;
    }


    public void setupOsmSource(Map map){
        OSMSource source = new OSMSource();
        TileLayer tileLayer = new TileLayer();
        tileLayer.setSource(source);
        map.setBackgroundLayer(tileLayer);
        map.addViewMoveEndEventListener(e->{
            System.out.println("X="+e.getCenter().getX()+"Y="+e.getCenter().getY());
            System.out.println("max:"+e.getExtent().getMaxX()+"min:"+e.getExtent().getMinX());
            if(e.getCenter().getX() >4048971.640714576) {
                map.setCenter(new Coordinate(4048971.640714576,e.getCenter().getY()));
            }
            if(e.getCenter().getX() < 4026891.3046239945) {
                map.setCenter(new Coordinate(4026891.3046239945,e.getCenter().getY()));
            }
            if(e.getCenter().getY()>6456434.06935033){
                map.setCenter(new Coordinate(e.getCenter().getX(),6456434.06935033));
            }
            if(e.getCenter().getY()<6433537.453433437){
                map.setCenter(new Coordinate(e.getCenter().getX(),6433537.453433437));
            }
            if(map.getView().getZoom()<12){
                View view = map.getView();
                view.setZoom(12);
            }
        });
        Coordinate coordinate = Coordinate.fromLonLat(36.2304,49.9935);
        map.getView().setCenter(coordinate);
        map.getView().setZoom(15);

    }

}
