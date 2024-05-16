package com.prueba.tecnica.domain.api;

import com.prueba.tecnica.domain.model.Person;

public interface IPersonServicePort {

    void savePerson(Person person);

    void updatePerson(Long id, Person person);

    void deletePerson(Long id);
}
