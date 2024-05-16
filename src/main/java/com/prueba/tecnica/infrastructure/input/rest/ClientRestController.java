package com.prueba.tecnica.infrastructure.input.rest;

import com.prueba.tecnica.application.dto.ClientRequest;
import com.prueba.tecnica.application.dto.ClientResponse;
import com.prueba.tecnica.application.handler.IClientHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Validated
public class ClientRestController {

    private final IClientHandler clientHandler;

    @Operation(summary = "Add a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created object", content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid object", content = @Content)
    })
    @PostMapping
    public ResponseEntity<String> saveClient(@RequestBody ClientRequest clientRequest) {
        clientHandler.saveClient(clientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all the Clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All clients returned", content = @Content),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        return ResponseEntity.ok(clientHandler.getAllClients());
    }

    @Operation(summary = "Modify a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modified object", content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid object ", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> modifyClient(@PathVariable(value = "id") Long clientId, @RequestBody ClientRequest clientRequest) {
        clientHandler.updateClient(clientId, clientRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Delete a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete object", content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid object ", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable(value = "id") Long clientId) {
        clientHandler.deleteClient(clientId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
