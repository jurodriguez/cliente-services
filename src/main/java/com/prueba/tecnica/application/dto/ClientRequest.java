package com.prueba.tecnica.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequest {

    @NotBlank(message = "Client name field is required")
    private String name;

    @NotBlank(message = "Client gender field is required")
    private String gender;

    @NotNull(message = "Client age field is required")
    private Integer age;

    @NotBlank(message = "Client identification field is required")
    private String identification;

    @NotBlank(message = "Client address field is required")
    private String address;

    @NotBlank(message = "Client phone field is required")
    private String phone;

    @NotBlank(message = "clientID field is required")
    private String clientId;

    @NotNull(message = "The password field is required")
    private String password;

    @NotBlank(message = "Status field is required")
    private String status;
}
