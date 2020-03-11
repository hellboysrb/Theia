package com.example.smigic.mappers;

import com.example.smigic.domain.Person;
import com.example.smigic.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDTO toDTO(Person person);
    Person toEntity(PersonDTO personDTO);

    void update(@MappingTarget Person toUpdate, Person person);
}
