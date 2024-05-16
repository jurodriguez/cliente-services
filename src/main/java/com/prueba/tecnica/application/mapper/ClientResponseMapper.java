package com.prueba.tecnica.application.mapper;

import com.prueba.tecnica.application.dto.ClientResponse;
import com.prueba.tecnica.domain.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ClientResponseMapper {

    ClientResponse toResponse(Client client);

    List<ClientResponse> toResponseList(List<Client> clientList);
}
