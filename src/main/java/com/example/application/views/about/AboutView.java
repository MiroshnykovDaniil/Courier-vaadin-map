package com.example.application.views.about;

import com.example.application.model.Business;
import com.example.application.service.BusinessGroupService;
import com.example.application.service.BusinessService;
import com.example.application.service.MarkerService;
import com.example.application.views.BusinessView;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.map.configuration.Coordinate;
import com.vaadin.flow.component.map.configuration.View;
import com.vaadin.flow.component.map.configuration.feature.MarkerFeature;
import com.vaadin.flow.component.map.configuration.layer.TileLayer;
import com.vaadin.flow.component.map.configuration.source.OSMSource;
import com.vaadin.flow.component.map.configuration.source.XYZSource;
import com.vaadin.flow.component.map.configuration.style.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
@Component
public class AboutView extends VerticalLayout {


    public AboutView(@Autowired BusinessGroupService businessGroupService,
                     @Autowired MarkerService markerService) {
        setSpacing(false);
        add(new H2("Виберіть звідки замовити"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
        add(new BusinessView(businessGroupService,markerService));

        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));
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
    public void setupMapSource(Map map){
        XYZSource.Options sourceOptions = new XYZSource.Options();
        // set the URL pattern for the map service containing x, y, and z parameters
        // mapbox requires an access token, register on
        // mapbox.com to get one, and place it in the line below
        sourceOptions.setUrl("https://api.mapbox.com/styles/v1/danielq911/ckqaqbjzm01az18o1enrr220c/tiles/512/{z}/{x}/{y}?access_token=pk.eyJ1IjoiZGFuaWVscTkxMSIsImEiOiJja3FhcWFrYTYwZmZ4Mm9sOWRra2s5dXo3In0.lLMOIiGDPoSYRcCtPJQhKw");
        // using a map service usually requires setting
        // attributions with copyright notices
        sourceOptions.setAttributions(List.of(
                "<a href=\"https://www.mapbox.com/about/maps/\">© Mapbox</a>",
                "<a href=\"https://www.openstreetmap.org/about/\">© OpenStreetMap</a>"
        ));
        sourceOptions.setAttributionsCollapsible(false);
        XYZSource source = new XYZSource(sourceOptions);
        TileLayer tileLayer = new TileLayer();
        tileLayer.setSource(source);
        map.setBackgroundLayer(tileLayer);

        Coordinate coordinate = Coordinate.fromLonLat(36.2304,49.9935);
        map.getView().setCenter(coordinate);
        map.getView().setZoom(13);
    }
}
