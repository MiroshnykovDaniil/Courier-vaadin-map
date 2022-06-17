package com.example.application.model;

import com.vaadin.flow.component.map.configuration.style.Icon;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
public class Marker {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @OneToOne
    private BusinessGroup businessGroup;
    private String icon;

    public BusinessGroup getBusinessGroup() {
        return businessGroup;
    }

    public void setBusinessGroup(BusinessGroup businessGroup) {
        this.businessGroup = businessGroup;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String iconImg) {
        this.icon = iconImg;
    }
}
