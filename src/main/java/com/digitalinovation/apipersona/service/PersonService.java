package com.digitalinovation.apipersona.service;

import com.digitalinovation.apipersona.dto.MessageResponseDTO;
import com.digitalinovation.apipersona.dto.request.PersonDTO;
import com.digitalinovation.apipersona.entity.Person;
import com.digitalinovation.apipersona.exception.PersonFoundException;
import com.digitalinovation.apipersona.mapper.PersonMapper;
import com.digitalinovation.apipersona.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonService {
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
            Person personToSave= personMapper.toModel(personDTO);

            Person savedPerson = personRepository.save(personToSave);
            return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
            return allPeople.stream()
                    .map(personMapper::toDTO)
                    .collect(Collectors.toList());
    }

    public PersonDTO findByid(Long id) throws PersonFoundException {
        Person person = new VerifyIfExists(id).invoke();

        return personMapper.toDTO(person);

    }

    public void delete(long id) throws PersonFoundException {
        new VerifyIfExists(id).invoke();

        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonFoundException {
        new VerifyIfExists(id).invoke();
        Person personToUpdate= personMapper.toModel(personDTO);

        Person updatePerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatePerson.getId(), "Update person with ID ");
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    private class VerifyIfExists {
        private Long id;

        public VerifyIfExists(Long id) {
            this.id = id;
        }

        public Person invoke() throws PersonFoundException {
            return personRepository.findById(id)
                    .orElseThrow(() ->new PersonFoundException(id));
        }
    }
}
