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

}
