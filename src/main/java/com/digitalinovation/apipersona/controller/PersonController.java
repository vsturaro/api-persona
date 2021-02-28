package com.digitalinovation.apipersona.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController //informar q é um controlador
@RequestMapping("api/v1/people") //mapeamento com versão

public class PersonController {
    @GetMapping
    public String getBook() {
        return "API Test";
    }
}
