package com.prueba.tecnica.factory;

import com.prueba.tecnica.domain.model.Client;

public class FactoryClientDataTest {

    public static Client getClient() {
        Client client = new Client();
        client.setPersonId(1L);
        client.setClientId("1");
        client.setPassword("6547");
        client.setStatus("true");

        return client;
    }

    public static Client getClient2() {
        return new Client(null, null, null, null, null, null, null, 2L, 2L, "2", "1234", "true");
    }

    public static Object[] getObject() {
        Object[] object = new Object[5];
        object[0] = "juan";
        object[1] = "cra 15 #54-78";
        object[2] = "3225478963";
        object[3] = "7894";
        object[4] = "true";

        return object;
    }
}
