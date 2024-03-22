package ru.mis.uchastkovy.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChartController {

    @GetMapping("/")
    public String getMain() {
        return "main-page.html";
    }

    @GetMapping("/distr")
    public String getDistrMap() {
        return "district-map.html";
    }

}