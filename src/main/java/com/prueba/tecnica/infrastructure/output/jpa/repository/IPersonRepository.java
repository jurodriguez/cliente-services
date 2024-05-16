package com.prueba.tecnica.infrastructure.output.jpa.repository;

import com.prueba.tecnica.infrastructure.output.jpa.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<PersonEntity, Long> {
}
