package com.anpilov.onlinebank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




/**
 This class represents the configuration for Spring MVC, which is used to
 configure the web application with Spring Framework. It implements the
 WebMvcConfigurer interface to provide custom configuration options.
 The class adds view controllers to the registry, which allow the mapping of
 URLs to views without the need for a controller.
 @author Anpilov Kirill
 @version 1.0
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }
}
