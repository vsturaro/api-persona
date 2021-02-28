package com.digitalinovation.apipersona.controller;

import com.digitalinovation.apipersona.dto.MessageResponseDTO;
import com.digitalinovation.apipersona.entity.Person;
import com.digitalinovation.apipersona.repository.PersonRepository;
import com.digitalinovation.apipersona.service.PersonService;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController //informar q é um controlador
@RequestMapping("api/v1/people") //mapeamento com versão

public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }
}
