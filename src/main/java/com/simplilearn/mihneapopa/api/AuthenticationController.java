package com.simplilearn.mihneapopa.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

    @GetMapping(value = "login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "home")
    public String home() {
        return "home";
    }

    @PostMapping("/home")
    public String handleHomePost() {
        return "home";
    }

    @GetMapping(value = "products")
    public String products() {
        return "product";
    }
}
