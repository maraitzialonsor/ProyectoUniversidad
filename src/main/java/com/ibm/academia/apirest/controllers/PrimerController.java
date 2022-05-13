package com.ibm.academia.apirest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resapi")
public class PrimerController {
    @GetMapping
    public String holamundo(){
        return "holamundo";
    }
}
