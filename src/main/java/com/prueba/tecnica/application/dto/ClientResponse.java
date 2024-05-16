package com.prueba.tecnica.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResponse {
    private String name;

    private String address;

    private String phone;

    private String password;

    private String status;

    public static ClientResponse fromClient(Object[] obj) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setName((String) obj[0]);
        clientResponse.setAddress((String) obj[1]);
        clientResponse.setPhone((String) obj[2]);
        clientResponse.setPassword((String) obj[3]);
        clientResponse.setStatus((String) obj[4]);

        return clientResponse;
    }
}
