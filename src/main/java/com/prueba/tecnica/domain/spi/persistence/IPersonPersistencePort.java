package com.prueba.tecnica.domain.spi.persistence;

import com.prueba.tecnica.domain.model.Person;

public interface IPersonPersistencePort {

    void savePerson(Person person);

    Person getPersonById(Long id);

    void deletePerson(Person person);
}
