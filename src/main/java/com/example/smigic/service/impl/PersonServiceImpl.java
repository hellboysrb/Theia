package com.example.smigic.service.impl;

import com.example.smigic.domain.Person;
import com.example.smigic.dto.PersonDTO;
import com.example.smigic.mappers.PersonListMapper;
import com.example.smigic.mappers.PersonMapper;
import com.example.smigic.repository.PersonRepository;
import com.example.smigic.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    private final PersonListMapper personListMapper;


    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper, PersonListMapper personListMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.personListMapper = personListMapper;
    }

    @Override
    public PersonDTO getById(Long personId) {
        Optional<Person> person = personRepository.findById(personId);
        return personMapper.toDTO(person.get()); // .get() jer je Optional
    }

    @Override
    public PersonDTO saveParson(PersonDTO personDto) {
        Person person = personMapper.toEntity(personDto);
        return personMapper.toDTO(personRepository.save(person));
    }

    @Override
    public PersonDTO updatePerson(Long personId, PersonDTO personDto) {
        Person personUpdate = personMapper.toEntity(personDto);
        Optional<Person> personToUpdateOptional = personRepository.findById(personId);
        Person person = null;
        if(personToUpdateOptional.isPresent()) {
            person = personToUpdateOptional.get();
        }
        personMapper.update(person, personUpdate);
        return personMapper.toDTO(personRepository.save(person));
    }

    @Override
    public void deletePerson(Long personId) {
        personRepository.deleteById(personId);
    }

    @Override
    public PersonDTO getByFirstName(String firstName) {
        Person person = personRepository.findByFirstName(firstName);
        return personMapper.toDTO(person);
    }

    @Override
    public List<PersonDTO> getAll() {
        Iterable<Person> personList = personRepository.findAll();
        return personListMapper.toDTOList(personList);
    }
}
