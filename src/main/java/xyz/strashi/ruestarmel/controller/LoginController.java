package xyz.strashi.ruestarmel.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/")
    public String getUser(Model model, HttpServletRequest request) {
        Boolean admin = false;
        if(request.isUserInRole("ROLE_ADMIN"))
            admin = true;

        model.addAttribute("admin",admin);
        return "index";
    }

    @GetMapping("/admin")
    public String getAdmin() {

        return "admin";
    }
}
