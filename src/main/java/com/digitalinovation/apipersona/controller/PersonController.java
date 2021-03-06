package com.digitalinovation.apipersona.controller;

import com.digitalinovation.apipersona.dto.MessageResponseDTO;
import com.digitalinovation.apipersona.dto.request.PersonDTO;
import com.digitalinovation.apipersona.entity.Person;
import com.digitalinovation.apipersona.exception.PersonFoundException;
import com.digitalinovation.apipersona.repository.PersonRepository;
import com.digitalinovation.apipersona.service.PersonService;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController //informar q é um controlador
@RequestMapping("api/v1/people") //mapeamento com versão
@AllArgsConstructor(onConstructor = @__(@Autowired)) //injeta dependencia e dispensa o cosntrutor
public class PersonController {

    private PersonService personService;

    //dispensado pelo uso da linha 22
//    @Autowired
//    public PersonController(PersonService personService) {        this.personService = personService;
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonFoundException {
        return personService.findByid(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonFoundException {
    return personService.updateById(id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable long id) throws PersonFoundException {
        personService.delete(id);
    }
}
