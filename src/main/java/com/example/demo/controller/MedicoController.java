package com.example.demo.controller;

import com.example.demo.dto.MedicoDTO;
import com.example.demo.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medico")
@Tag(name = "Medico", description = "API per la gestione dei medici")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @Operation(
            summary = "Get all medici",
            description = "Restituisce tutti i medici",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista medici restituita con successo")
            }
    )
    @GetMapping
    public List<MedicoDTO> findAll() {
        return medicoService.findAll();
    }

    @Operation(
            summary = "Get medico by ID",
            description = "Restituisce un medico tramite ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Medico trovato"),
                    @ApiResponse(responseCode = "404", description = "Medico non trovato")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> findById(@PathVariable Long id) {
        return medicoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create medico",
            description = "Crea un nuovo medico",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Medico creato con successo")
            }
    )
    @PostMapping
    public MedicoDTO create(@RequestBody MedicoDTO medicoDTO) {
        return medicoService.save(medicoDTO);
    }

    @Operation(
            summary = "Delete medico",
            description = "Elimina un medico tramite ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Medico eliminato con successo"),
                    @ApiResponse(responseCode = "404", description = "Medico non trovato")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}