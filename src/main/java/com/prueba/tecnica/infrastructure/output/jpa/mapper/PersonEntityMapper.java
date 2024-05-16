package com.prueba.tecnica.infrastructure.output.jpa.mapper;

import com.prueba.tecnica.domain.model.Person;
import com.prueba.tecnica.infrastructure.output.jpa.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PersonEntityMapper {

    PersonEntity toEntity(Person person);

    Person toPerson(PersonEntity personEntity);

    List<Person> toPersonList(List<PersonEntity> personEntityList);
}
