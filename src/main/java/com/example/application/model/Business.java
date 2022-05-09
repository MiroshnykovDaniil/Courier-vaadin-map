package com.example.application.model;

import com.vaadin.flow.component.map.configuration.Coordinate;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Business")
public class Business{
    @Id
    @GeneratedValue(generator = "uuid2")
    private String id;
    private String title;
    private String address;

    @OneToMany
    @Column(name = "menu")
    private Set<Item> menu;
    @Embedded
    @Column(name = "coordinate")
    private Coordinate coordinate;

    // not sure if it needed
    public void addToMenu(Item item){
        menu.add(item);
    }
    public void removeFromMenu(Item item){
        menu.remove(item);
    }

    // Purely for serialization/deserialization
    protected Business(){
        id=null;
        title=null;
        address=null;
        menu=null;
        coordinate=null;
    }



    public Business(String title, String address, Set<Item> menu, Coordinate coordinate){
        this.id=null;
        this.title = title;
        this.address = address;
        this.menu = menu;
        this.coordinate = coordinate;
    }

    public static BusinessBuilder builder(){
        return new BusinessBuilder();
    }
    public static class BusinessBuilder{
        private String title;
        private String address;
        private Set<Item> menu;
        private Coordinate coordinate;

        public BusinessBuilder setTitle(String title){
            this.title=title;
            return this;
        }
        public BusinessBuilder setAddress(String address){
            this.address = address;
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
            Business business = new Business(title,address,menu,coordinate);
            return business;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Item> getMenu() {
        return menu;
    }

    public void setMenu(Set<Item> menu) {
        this.menu = menu;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
