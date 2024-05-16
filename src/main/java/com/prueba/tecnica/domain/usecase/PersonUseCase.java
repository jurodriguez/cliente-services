package com.prueba.tecnica.domain.usecase;

import com.prueba.tecnica.domain.api.IPersonServicePort;
import com.prueba.tecnica.domain.exception.TechnicalException;
import com.prueba.tecnica.domain.model.Person;
import com.prueba.tecnica.domain.model.enums.TechnicalMessage;
import com.prueba.tecnica.domain.spi.persistence.IPersonPersistencePort;

public class PersonUseCase implements IPersonServicePort {

    private final IPersonPersistencePort personPersistencePort;

    public PersonUseCase(IPersonPersistencePort personPersistencePort) {
        this.personPersistencePort = personPersistencePort;
    }

    @Override
    public void savePerson(Person person) {
        personPersistencePort.savePerson(person);
    }

    @Override
    public void updatePerson(Long id, Person person) {
        Person previusPerson = getPreviusPerson(id);
        updateData(previusPerson, person);
        personPersistencePort.savePerson(previusPerson);
    }

    @Override
    public void deletePerson(Long id) {
        Person previusPerson = getPreviusPerson(id);
        personPersistencePort.deletePerson(previusPerson);
    }

    private Person getPreviusPerson(Long id) {
        Person previusPerson = personPersistencePort.getPersonById(id);
        if (previusPerson == null) throw new TechnicalException(TechnicalMessage.PERSON_INFORMATION_NOT_FOUND);
        return previusPerson;
    }

    private void updateData(Person previusPerson, Person person) {
        previusPerson.setName(person.getName());
        previusPerson.setGender(person.getGender());
        previusPerson.setAge(person.getAge());
        previusPerson.setIdentification(person.getIdentification());
        previusPerson.setAddress(person.getAddress());
        previusPerson.setPhone(person.getPhone());
    }
}
