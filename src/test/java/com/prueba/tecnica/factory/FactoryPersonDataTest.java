package com.prueba.tecnica.factory;

import com.prueba.tecnica.domain.model.Person;

public class FactoryPersonDataTest {

    public static Person getPerson() {
        Person person = new Person();
        person.setName("Juan Osorio");
        person.setGender("Masculino");
        person.setAge(34);
        person.setIdentification("987456111");
        person.setAddress("13 junio y Equinoccial");
        person.setPhone("098874587");

        return person;
    }

    public static Person getPerson2() {
        return new Person(1L, "Juan Rodriguez", "Masculino", 28, "4567891", "cra 15 #79-45", "3214567897");
    }
}
