package com.example.application.views;

import com.example.application.model.Business;
import com.example.application.service.BusinessService;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.map.configuration.Coordinate;
import com.vaadin.flow.component.map.configuration.View;
import com.vaadin.flow.component.map.configuration.feature.MarkerFeature;
import com.vaadin.flow.component.map.configuration.layer.TileLayer;
import com.vaadin.flow.component.map.configuration.source.OSMSource;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.HashMap;

public class BusinessView extends VerticalLayout {

    HashMap<MarkerFeature, Business> businessLookup = new HashMap<>();

    Map map = new Map();

    public BusinessView(){
        this.setHeightFull();
        this.setPadding(false);
        this.setSpacing(false);
        this.setWidth("1200px");
        this.setClassName("business-view");
        this.getStyle()
                .set("flexShrink", "0")
                .set("background-color", "#FFFCF9")
                .set("text-align", "center");
        this.add(map);
        setupOsmSource(map);
        addIcons(map);

    }
    public void addIcons(Map map) {

        Dialog dialog = new Dialog();
        VerticalLayout dialogLayout = createDialogLayout();
        dialog.add(dialogLayout);

        BusinessService service = new BusinessService();
        service.initData();
        service.addIcons(map);


        map.addFeatureClickListener(e -> {
            dialog.open();
            dialog.add();
        });
    }

    private static VerticalLayout createDialogLayout() {

        TextField title = new TextField("Title");
        TextField description = new TextField("description");

        VerticalLayout dialogLayout = new VerticalLayout(title,
                description);
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
