package com.prueba.tecnica.domain.model;

public class Client extends Person {

    private Long id;
    private Long personId;
    private String clientId;
    private String password;
    private String status;

    public Client() {
    }

    public Client(Long personId, String name, String gender, Integer age, String identification, String address, String phone, Long id, Long personId1, String clientId, String password, String status) {
        super(personId, name, gender, age, identification, address, phone);
        this.id = id;
        this.personId = personId1;
        this.clientId = clientId;
        this.password = password;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public Long getPersonId() {
        return personId;
    }

    @Override
    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
