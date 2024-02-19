package ru.mis.uchastkovy.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class UserController {

    @GetMapping
    public String loginForm() {
        return "login";
    }

    @PostMapping
    public String loginForm(@RequestParam String username, @RequestParam String password) {
        return "login";
    }

}
