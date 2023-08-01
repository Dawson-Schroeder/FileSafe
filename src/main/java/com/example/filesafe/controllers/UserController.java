package com.example.filesafe.controllers;

import com.example.filesafe.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final UserRepository usersDao;


    public UserController(UserRepository usersDao) {
        this.usersDao = usersDao;
    }

    @GetMapping("/home")
    public String showHome(){
        return "index";
    }
}
