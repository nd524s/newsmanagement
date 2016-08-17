package com.epam.newsmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Никита on 8/12/2016.
 */
@Controller
@RequestMapping("/")
public class LoginController {
    private static final String LOGIN_VIEW = "login";

    @RequestMapping("/signIn")
    public String showLoginPage() {
        return LOGIN_VIEW;
    }
}
