package com.example.application.model;

import com.vaadin.flow.component.map.configuration.Coordinate;

import java.util.Set;

public class Business{

    final private String id;
    final private String title;
    final private String address;
    final private Set<Item> menu;
    final private Coordinate coordinate;

    // not sure if it needed
    public void addToMenu(Item item){
        menu.add(item);
    }
    public void removeFromMenu(Item item){
        menu.remove(item);
    }

    public Business(BusinessBuilder builder){
        this.id=builder.id;
        this.title=builder.title;
        this.address=builder.address;
        this.menu=builder.menu;
        this.coordinate=builder.coordinate;
    }

    public static class BusinessBuilder{
        private static int i=0;
        private String id;
        private String title;
        private String address;
        private Set<Item> menu;
        private Coordinate coordinate;

        public BusinessBuilder setTitle(String title){
            this.title=title;
            return this;
        }
        public BusinessBuilder setAddress(String adress){
            this.address = adress;
            return this;
        }
        public BusinessBuilder setMenu(Set<Item> menu){
            this.menu = menu;
            return this;
        }
        public BusinessBuilder setCoordinate(Coordinate coordinate){
            this.coordinate = coordinate;
            return this;
        }
        public Business build(){
            //temp
            this.id= String.valueOf(i);
            i++;
            //

            Business business = new Business(this);
            return business;
        }


    }


}
