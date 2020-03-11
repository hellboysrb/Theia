package com.example.smigic.web;

import com.example.smigic.dto.PersonDTO;
import com.example.smigic.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController  {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping(path = "/persons/{id}")
    PersonDTO getById(@PathVariable Long id){ return personService.getById(id);}

    @PostMapping(path = "/persons")
    PersonDTO saveParson(@RequestBody PersonDTO person){ return personService.saveParson(person);}

    @PutMapping(path = "/persons/{id}")
    PersonDTO updatePerson(@RequestBody PersonDTO person, @PathVariable Long id){ return personService.updatePerson(id, person);}

    @DeleteMapping(path = "/persons/{id}")
    void deletePerson(@PathVariable(name="id") Long personId){ personService.deletePerson(personId);}

    @GetMapping(path = "/persons/person/{firstName}")
    PersonDTO getByFirstName(@PathVariable String firstName){ return personService.getByFirstName(firstName); }

    @GetMapping(path = "/persons")
    List<PersonDTO> getAll(){ return personService.getAll(); };
}
