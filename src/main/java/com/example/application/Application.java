package com.example.application;

import com.example.application.datainit.DataInit;
import com.example.application.model.Business;
import com.example.application.model.BusinessGroup;
import com.example.application.model.Item;
import com.example.application.service.BusinessGroupService;
import com.example.application.service.BusinessService;
import com.example.application.service.ItemsService;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "vaadin-mapbox")
@PWA(name = "vaadin-mapbox", shortName = "vaadin-mapbox", offlineResources = {})
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {


    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }




}
