package com.prueba.tecnica.infrastructure.output.jpa.repository;

import com.prueba.tecnica.infrastructure.output.jpa.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query("SELECT per.name, per.address, per.phone, cli.password, cli.status FROM ClientEntity cli JOIN PersonEntity per ON cli.personId = per.personId")
    List<Object[]> getAllClientsWithPerson();
}
