package com.prueba.tecnica.infrastructure.configuration;

import com.prueba.tecnica.application.mapper.ClientResponseMapper;
import com.prueba.tecnica.domain.api.IClientServicePort;
import com.prueba.tecnica.domain.api.IPersonServicePort;
import com.prueba.tecnica.domain.spi.persistence.IClientPersistencePort;
import com.prueba.tecnica.domain.spi.persistence.IPersonPersistencePort;
import com.prueba.tecnica.domain.usecase.ClientUseCase;
import com.prueba.tecnica.domain.usecase.PersonUseCase;
import com.prueba.tecnica.infrastructure.output.jpa.adapter.ClientJpaAdapter;
import com.prueba.tecnica.infrastructure.output.jpa.adapter.PersonJpaAdapter;
import com.prueba.tecnica.infrastructure.output.jpa.mapper.ClientEntityMapper;
import com.prueba.tecnica.infrastructure.output.jpa.mapper.PersonEntityMapper;
import com.prueba.tecnica.infrastructure.output.jpa.repository.IClientRepository;
import com.prueba.tecnica.infrastructure.output.jpa.repository.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IClientRepository clientRepository;

    private final ClientEntityMapper clientEntityMapper;

    private final IPersonRepository personRepository;

    private final PersonEntityMapper personEntityMapper;

    @Bean
    public IClientPersistencePort clientPersistencePort() {
        return new ClientJpaAdapter(clientRepository, clientEntityMapper);
    }

    @Bean
    public IPersonPersistencePort personPersistencePort() {
        return new PersonJpaAdapter(personRepository, personEntityMapper);
    }

    @Bean
    public IPersonServicePort personServicePort() {
        return new PersonUseCase(personPersistencePort());
    }

    @Bean
    public IClientServicePort clientServicePort() {
        return new ClientUseCase(clientPersistencePort(), personServicePort());
    }
}
