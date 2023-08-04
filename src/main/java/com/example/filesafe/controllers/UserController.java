package com.example.filesafe.controllers;

import com.example.filesafe.models.User;
import com.example.filesafe.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
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

    @GetMapping("/register")
    public String showRegistrationForm(){
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam(name = "username") String username,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "password") String password,
                               @RequestParam(name = "passwordConfirmation") String passwordConfirm,
                               RedirectAttributes redirectAttributes,
                               HttpServletRequest request) {
        User existingUser = usersDao.findUserByUsername(username);
        User existingEmail = usersDao.findUserByEmail(email);

        if (existingUser != null || existingEmail != null) {
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

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", true);
        }
        return "login";
    }
    @PostMapping("/login")
    public String login() {
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String showProfile(Model model) throws Exception{
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        user = usersDao.findUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("id", userId);
        return "profile";
    }


//    END
}
