package com.example.smigic.service;

import com.example.smigic.dto.PersonDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public interface PersonService {
    PersonDTO getById(Long personId);
    PersonDTO saveParson(PersonDTO person);
    PersonDTO updatePerson(Long personId, PersonDTO person);
    void deletePerson(Long personId);
    PersonDTO getByFirstName(String firstName);
    List<PersonDTO> getAll();
}
