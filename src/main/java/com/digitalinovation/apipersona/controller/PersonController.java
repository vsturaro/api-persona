package com.digitalinovation.apipersona.controller;

import com.digitalinovation.apipersona.dto.MessageResponseDTO;
import com.digitalinovation.apipersona.entity.Person;
import com.digitalinovation.apipersona.repository.PersonRepository;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController //informar q é um controlador
@RequestMapping("api/v1/people") //mapeamento com versão

public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public MessageResponseDTO createPerson(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }
}
