package com.example.application.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "BusinessGroup")
public class BusinessGroup {

    @Id
    @GeneratedValue(generator = "uuid2")
    private String id;
    private String title;

    @OneToMany
    @Column(name = "business")
    private Set<Business> businesses;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(Set<Business> businesses) {
        this.businesses = businesses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
