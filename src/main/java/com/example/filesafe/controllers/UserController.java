package com.example.filesafe.controllers;

import com.example.filesafe.models.User;
import com.example.filesafe.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class UserController {
    private final UserRepository usersDao;
    private final PasswordEncoder passwordEncoder;


    public UserController(UserRepository usersDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/home")
    public String showHome(){
        return "index";
    }

    @GetMapping("register")
    public String showRegistrationForm(){
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam(name = "username") String username,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "password") String password,
                               @RequestParam(name = "passwordConfirmation") String passwordConfirm,
                               RedirectAttributes redirectAttributes,
                               HttpServletRequest request) {
        User exsistingUser = usersDao.findUserByUsername(username);
        User exsistingEmail = usersDao.findUserByEmail(email);

        if (exsistingUser != null || exsistingEmail != null) {
            redirectAttributes.addAttribute("userExists", true);
            return "redirect:/register?error";
        }
        if (password.equals(passwordConfirm)){
            password = passwordEncoder.encode(password);
            usersDao.save(new User(email, username, password));
            return "redirect:/login";
        } else {
            redirectAttributes.addAttribute("passwordMismatch", true);
            return "redirect:/register?error";
        }
    }
}
