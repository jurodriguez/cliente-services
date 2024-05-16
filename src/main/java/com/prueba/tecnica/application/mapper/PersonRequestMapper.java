package com.prueba.tecnica.application.mapper;

import com.prueba.tecnica.application.dto.ClientRequest;
import com.prueba.tecnica.domain.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PersonRequestMapper {
    Person toPerson(ClientRequest clientRequest);
}
