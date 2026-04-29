package com.example.demo.controller;

import com.example.demo.dto.PersonaDTO;
import com.example.demo.service.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persona")
@Tag(name = "Persona", description = "API per la gestione delle persone")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Operation(
            summary = "Get all persone",
            description = "Restituisce tutte le persone",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista persone restituita con successo")
            }
    )
    @GetMapping
    public List<PersonaDTO> findAll() {
        return personaService.findAll();
    }

    @Operation(
            summary = "Get persona by ID",
            description = "Restituisce una persona tramite ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Persona trovata"),
                    @ApiResponse(responseCode = "404", description = "Persona non trovata")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> findById(@PathVariable Long id) {
        return personaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create persona",
            description = "Crea una nuova persona",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Persona creata con successo")
            }
    )
    @PostMapping
    public PersonaDTO create(@RequestBody PersonaDTO personaDTO) {
        return personaService.save(personaDTO);
    }

    @Operation(
            summary = "Delete persona",
            description = "Elimina una persona tramite ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Persona eliminata con successo"),
                    @ApiResponse(responseCode = "404", description = "Persona non trovata")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}