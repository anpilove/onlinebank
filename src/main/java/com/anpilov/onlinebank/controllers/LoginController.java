package com.anpilov.onlinebank.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**

 The LoginController class handles requests related to user login.

 This class is a Spring Controller that maps requests to the "/login" URL to the login() method. The method returns

 the "login" view, which renders the login page for the user to enter their credentials.

 This class is annotated with the @Controller annotation to indicate that it is a Spring Controller, and the @GetMapping

 annotation is used to map HTTP GET requests to the login() method.
 **/
@Controller
public class LoginController {
    /**
     Handles HTTP GET requests to the "/login" URL.
     This method returns the "login" view, which renders the login page for the user to enter their credentials.
     @return The name of the view that should be rendered for the "/login" URL.
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
