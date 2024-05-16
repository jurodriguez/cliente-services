package com.prueba.tecnica.domain.usecase;

import com.prueba.tecnica.domain.api.IPersonServicePort;
import com.prueba.tecnica.domain.exception.TechnicalException;
import com.prueba.tecnica.domain.model.Client;
import com.prueba.tecnica.domain.model.Person;
import com.prueba.tecnica.domain.spi.persistence.IPersonPersistencePort;
import com.prueba.tecnica.factory.FactoryClientDataTest;
import com.prueba.tecnica.factory.FactoryPersonDataTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class PersonUseCaseTest {

    @InjectMocks
    PersonUseCase personUseCase;

    @Mock
    IPersonPersistencePort personPersistencePort;

    @Mock
    IPersonServicePort personServicePort;

    @Test
    void mustSavePerson() {
        Person person = FactoryPersonDataTest.getPerson();

        personUseCase.savePerson(person);

        //Then
        Mockito.verify(personPersistencePort).savePerson(Mockito.any(Person.class));
    }

    @Test
    void mustUpdatePerson() {
        Person person = FactoryPersonDataTest.getPerson();
        Person previusPerson = FactoryPersonDataTest.getPerson2();

        previusPerson.setName(person.getName());
        previusPerson.setGender(person.getGender());
        previusPerson.setAge(person.getAge());
        previusPerson.setIdentification(person.getIdentification());
        previusPerson.setAddress(person.getAddress());
        previusPerson.setPhone(person.getPhone());

        Mockito.when(personPersistencePort.getPersonById(Mockito.anyLong())).thenReturn(previusPerson);
        Mockito.when(personPersistencePort.getPersonById(previusPerson.getPersonId())).thenReturn(previusPerson);

        personUseCase.updatePerson(previusPerson.getPersonId(), person);

        //Then
        Mockito.verify(personPersistencePort).savePerson(previusPerson);
        assertEquals(previusPerson.getName(), person.getName());
        assertEquals(previusPerson.getGender(), person.getGender());
        assertEquals(previusPerson.getAge(), person.getAge());
        assertEquals(previusPerson.getIdentification(), person.getIdentification());
        assertEquals(previusPerson.getAddress(), person.getAddress());
        assertEquals(previusPerson.getPhone(), person.getPhone());
    }

    @Test
    void mustDeleteClient() {
        Person previusPerson = FactoryPersonDataTest.getPerson2();

        Mockito.when(personPersistencePort.getPersonById(previusPerson.getPersonId())).thenReturn(previusPerson);

        personUseCase.deletePerson(previusPerson.getPersonId());

        //Then
        Mockito.verify(personPersistencePort).deletePerson(previusPerson);
    }

    @Test
    void mustDeleteClientTechnicalException() {
        Person previusPerson = FactoryPersonDataTest.getPerson2();

        assertThrows(TechnicalException.class, () -> personUseCase.deletePerson(previusPerson.getPersonId()));

        //Then
        Mockito.verify(personPersistencePort, Mockito.never()).deletePerson(previusPerson);
    }
}
