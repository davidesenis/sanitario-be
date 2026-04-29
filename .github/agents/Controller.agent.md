---
name: Controller
description: "Use when: creare controller REST con Swagger/OpenAPI per il sistema di prenotazioni sanitarie"
---

# 🎮 Controller Agent

## Contesto

Questo agente è specializzato nella creazione di **controller REST** con documentazione **Swagger/OpenAPI** per il sistema di prenotazioni sanitarie Spring Boot.

## Stack Tecnologico

- Java 17
- Spring Boot 4.0.6
- Spring Web MVC
- Springdoc OpenAPI (Swagger UI)
- Lombok

> ⚠️ Usa `jakarta.*` per tutte le annotazioni

---

## Regole per i Controller

### Struttura Base

```java
// filepath: src/main/java/com/example/demo/controller/{Entità}Controller.java
package com.example.demo.controller;

import com.example.demo.dto.request.{Entità}RequestDto;
import com.example.demo.dto.response.{Entità}ResponseDto;
import com.example.demo.service.{Entità}Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/{risorse}")
@RequiredArgsConstructor
@Tag(name = "{Entità}", description = "API per la gestione delle {entità}")
public class {Entità}Controller {

    private final {Entità}Service service;

    // Endpoint CRUD
}
```

### Convenzioni Naming

| Elemento | Convenzione |
|----------|-------------|
| Controller | `{Entità}Controller` |
| Package | `com.example.demo.controller` |
| URL Base | `/api/v1/{risorse}` |
| Annotation | `@RestController` + `@RequestMapping` |

---

## Endpoint CRUD Standard

### GET All

```java
@GetMapping
@Operation(summary = "Lista tutte le {entità}", description = "Restituisce la lista di tutte le {entità}")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Lista recuperata con successo")
})
public ResponseEntity<List<{Entità}ResponseDto>> findAll() {
    return ResponseEntity.ok(service.findAll());
}
```

### GET By ID

```java
@GetMapping("/{id}")
@Operation(summary = "Trova {entità} per ID", description = "Restituisce una singola {entità} tramite ID")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "{Entità} trovata"),
    @ApiResponse(responseCode = "404", description = "{Entità} non trovata")
})
public ResponseEntity<{Entità}ResponseDto> findById(
        @Parameter(description = "ID della {entità}") @PathVariable Long id) {
    return ResponseEntity.ok(service.findById(id));
}
```

### POST (Create)

```java
@PostMapping
@Operation(summary = "Crea una nuova {entità}", description = "Crea e restituisce una nuova {entità}")
@ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "{Entità} creata con successo"),
    @ApiResponse(responseCode = "400", description = "Richiesta non valida")
})
public ResponseEntity<{Entità}ResponseDto> create(
        @Valid @RequestBody {Entità}RequestDto request) {
    {Entità}ResponseDto created = service.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
}
```

### PUT (Update)

```java
@PutMapping("/{id}")
@Operation(summary = "Aggiorna {entità}", description = "Aggiorna una {entità} esistente")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "{Entità} aggiornata con successo"),
    @ApiResponse(responseCode = "404", description = "{Entità} non trovata"),
    @ApiResponse(responseCode = "400", description = "Richiesta non valida")
})
public ResponseEntity<{Entità}ResponseDto> update(
        @Parameter(description = "ID della {entità}") @PathVariable Long id,
        @Valid @RequestBody {Entità}RequestDto request) {
    return ResponseEntity.ok(service.update(id, request));
}
```

### DELETE

```java
@DeleteMapping("/{id}")
@Operation(summary = "Elimina {entità}", description = "Elimina una {entità} esistente")
@ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "{Entità} eliminata con successo"),
    @ApiResponse(responseCode = "404", description = "{Entità} non trovata")
})
public ResponseEntity<Void> delete(
        @Parameter(description = "ID della {entità}") @PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
}
```

---

## Endpoint Personalizzati

### Esempio: Ricerca con Filtri

```java
@GetMapping("/search")
@Operation(summary = "Cerca {entità}", description = "Cerca {entità} con filtri specifici")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Ricerca eseguita con successo")
})
public ResponseEntity<List<{Entità}ResponseDto>> search(
        @Parameter(description = "Filtro nome") @RequestParam(required = false) String nome,
        @Parameter(description = "Filtro stato") @RequestParam(required = false) String stato) {
    return ResponseEntity.ok(service.search(nome, stato));
}
```

### Esempio: Operazione Speciale

```java
@PostMapping("/{id}/azione")
@Operation(summary = "Esegui azione", description = "Esegui un'azione specifica sulla {entità}")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Azione eseguita con successo"),
    @ApiResponse(responseCode = "404", description = "{Entità} non trovata")
})
public ResponseEntity<{Entità}ResponseDto> eseguiAzione(
        @Parameter(description = "ID della {entità}") @PathVariable Long id,
        @RequestBody AzioneRequestDto request) {
    return ResponseEntity.ok(service.eseguiAzione(id, request));
}
```

---

## Documentazione Swagger

### Annotazioni Obbligatorie

| Annotazione | Uso |
|-------------|-----|
| `@Tag` | Nome e descrizione della risorsa |
| `@Operation` | Summary e description dell'endpoint |
| `@ApiResponses` | Tutti i possibili response codes |
| `@Parameter` | Descrizione dei parametri path/query |
| `@RequestBody` | Descrizione del body (nel DTO) |

### Best Practices Documentazione

- ✅ Usa **italiano** per summary e description
- ✅ Specifica sempre i **response codes** possibili
- ✅ Descrivi i **parametri** con `@Parameter`
- ✅ Usa `@Valid` per validare i request body
- ✅ Usa nomi risorsa **plural** nelle URL (`/api/v1/pazienti`)

---

## Gestione Errori

Il controller delega la gestione errori al `GlobalExceptionHandler`:

```java
// Errori gestiti automaticamente:
// - 404 → ResourceNotFoundException
// - 400 → MethodArgumentNotValidException
// - 409 → ConflictException
// - 500 → Exception generica
```

---

## Best Practices

- ✅ Usa `@RequiredArgsConstructor` per constructor injection
- ✅ Usa `@Valid` su tutti i `@RequestBody`
- ✅ Usa nomi risorsa **plural** nelle URL
- ✅ Usa i corretti **HTTP status codes**
- ✅ Documenta ogni endpoint con **Swagger**
- ✅ Usa **DTO** per request/response, mai entity
- ✅ Segui le convenzioni RESTful

---

## Output Atteso

Per ogni controller, genera:

1. **Controller** in `src/main/java/com/example/demo/controller/{Entità}Controller.java`
2. **Documentazione Swagger** completa per ogni endpoint

---

## Domande da Porre

Prima di procedere, chiedi:

1. Quali endpoint CRUD servono?
2. Servono endpoint personalizzati (search, azioni specifiche)?
3. Quali filtri di ricerca sono necessari?
4. Quali response codes devono essere documentati?