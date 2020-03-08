package com.example.smigic.mappers;

import com.example.smigic.domain.Person;
import com.example.smigic.dto.PersonDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = PersonMapper.class, componentModel = "spring")
public interface PersonListMapper {
    List<PersonDTO> toDTOList(Iterable<Person> person);
}
