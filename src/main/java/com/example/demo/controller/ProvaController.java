package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prova")
public class ProvaController {
    
    @GetMapping(value = "/saluto")
    public String saluto(){
        return "Ciao Mondo";
    }
}
