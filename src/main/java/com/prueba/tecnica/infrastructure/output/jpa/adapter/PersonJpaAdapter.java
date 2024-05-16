package com.prueba.tecnica.infrastructure.output.jpa.adapter;

import com.prueba.tecnica.domain.model.Person;
import com.prueba.tecnica.domain.spi.persistence.IPersonPersistencePort;
import com.prueba.tecnica.infrastructure.output.jpa.entity.PersonEntity;
import com.prueba.tecnica.infrastructure.output.jpa.mapper.PersonEntityMapper;
import com.prueba.tecnica.infrastructure.output.jpa.repository.IPersonRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PersonJpaAdapter implements IPersonPersistencePort {

    private final IPersonRepository personRepository;

    private final PersonEntityMapper personEntityMapper;

    @Override
    public void savePerson(Person person) {
        PersonEntity entity = personRepository.save(personEntityMapper.toEntity(person));
        person.setPersonId(entity.getPersonId());
    }

    @Override
    public Person getPersonById(Long id) {
        Optional<PersonEntity> optionalPerson = personRepository.findById(id);
        PersonEntity personEntity = optionalPerson.orElse(null);
        return personEntityMapper.toPerson(personEntity);
    }

    @Override
    public void deletePerson(Person person) {
        personRepository.delete(personEntityMapper.toEntity(person));
    }
}
