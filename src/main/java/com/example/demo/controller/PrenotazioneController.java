package com.example.demo.controller;

import com.example.demo.dto.PrenotazioneDTO;
import com.example.demo.service.PrenotazioneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazione")
@Tag(name = "Prenotazione", description = "API per la gestione delle prenotazioni")
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    public PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    @Operation(
            summary = "Get all prenotazioni",
            description = "Restituisce tutte le prenotazioni",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista prenotazioni restituita con successo")
            }
    )
    @GetMapping
    public List<PrenotazioneDTO> findAll() {
        return prenotazioneService.findAll();
    }

    @Operation(
            summary = "Get prenotazione by ID",
            description = "Restituisce una prenotazione tramite ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Prenotazione trovata"),
                    @ApiResponse(responseCode = "404", description = "Prenotazione non trovata")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<PrenotazioneDTO> findById(@PathVariable Long id) {
        return prenotazioneService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create prenotazione",
            description = "Crea una nuova prenotazione",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Prenotazione creata con successo")
            }
    )
    @PostMapping
    public PrenotazioneDTO create(@RequestBody PrenotazioneDTO prenotazioneDTO) {
        return prenotazioneService.save(prenotazioneDTO);
    }

    @Operation(
            summary = "Delete prenotazione",
            description = "Elimina una prenotazione tramite ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Prenotazione eliminata con successo"),
                    @ApiResponse(responseCode = "404", description = "Prenotazione non trovata")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        prenotazioneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}