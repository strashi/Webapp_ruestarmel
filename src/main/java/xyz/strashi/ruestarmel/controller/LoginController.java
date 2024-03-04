package xyz.strashi.ruestarmel.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import xyz.strashi.ruestarmel.service.StorageService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
public class LoginController {
    private final StorageService storageService;

    @Autowired
    public LoginController(StorageService storageService){
        this.storageService = storageService;
    }

    @GetMapping("/login")
    public  String loginPage(){
        return "login";

    }
    @GetMapping("/")
    public String getUser(Model model, HttpServletRequest request) {
        boolean admin = false;
        if(request.isUserInRole("ROLE_ADMIN"))
            admin = true;

        model.addAttribute("admin",admin);
        model.addAttribute("files", storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileUpLoadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));
        return "index";
    }


}
