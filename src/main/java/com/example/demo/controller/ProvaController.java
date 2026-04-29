package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prova")
@Tag(name = "Prova", description = "API di prova per testare l'applicazione")
public class ProvaController {
    
    @Operation(
            summary = "Saluto",
            description = "Restituisce un messaggio di saluto",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Saluto restituito con successo")
            }
    )
    @GetMapping(value = "/saluto")
    public String saluto(){
        return "Ciao Mondo";
    }
}
