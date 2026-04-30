---
name: ServiceLayer
description: "Use when: implementare la logica di business e i service layer per il sistema di prenotazioni sanitarie"
---

# 🧩 Service Layer Agent

## Nota

Questo agente deve seguire la politica centrale condivisa: `.github/agents/AGENT_POLICY.md`.

---

## Contesto

Questo agente è specializzato nella creazione dei **service layer** che contengono la logica di business per il sistema di prenotazioni sanitarie Spring Boot.

## Stack Tecnologico

- Java 17
- Spring Boot 4.0.6
- Spring Data JPA
- Lombok
- MapStruct

> ⚠️ Usa `jakarta.*` per tutte le annotazioni

---

## Regole per i Service

### Struttura Base

```java
// filepath: src/main/java/com/example/demo/service/{Entità}Service.java
package com.example.demo.service;

import com.example.demo.dto.request.{Entità}RequestDto;
import com.example.demo.dto.response.{Entità}ResponseDto;
import com.example.demo.entity.{Entità};
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.{Entità}Mapper;
import com.example.demo.repository.{Entità}Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class {Entità}Service {

    private final {Entità}Repository repository;
    private final {Entità}Mapper mapper;

    // CRUD operations
}
```

### Convenzioni Naming

| Elemento | Convenzione |
|----------|-------------|
| Service | `{Entità}Service` |
| Package | `com.example.demo.service` |
| Annotation | `@Service` + `@RequiredArgsConstructor` |

---

## Operazioni CRUD Standard

### Find All

```java
@Transactional(readOnly = true)
public List<{Entità}ResponseDto> findAll() {
    return repository.findAll().stream()
            .map(mapper::toResponseDto)
            .toList();
}
```

### Find By ID

```java
@Transactional(readOnly = true)
public {Entità}ResponseDto findById(Long id) {
    {Entità} entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "{Entità} non trovato con id: " + id));
    return mapper.toResponseDto(entity);
}
```

### Create

```java
public {Entità}ResponseDto create({Entità}RequestDto request) {
    {Entità} entity = mapper.toEntity(request);
    {Entità} saved = repository.save(entity);
    return mapper.toResponseDto(saved);
}
```

### Update

```java
public {Entità}ResponseDto update(Long id, {Entità}RequestDto request) {
    {Entità} entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "{Entità} non trovato con id: " + id));
    
    mapper.updateEntityFromRequest(request, entity);
    {Entità} updated = repository.save(entity);
    return mapper.toResponseDto(updated);
}
```

### Delete

```java
public void delete(Long id) {
    if (!repository.existsById(id)) {
        throw new ResourceNotFoundException(
            "{Entità} non trovato con id: " + id);
    }
    repository.deleteById(id);
}
```

---

## Logica di Business Specifica

### Esempio: Prenotazione Service

```java
@Transactional
public PrenotazioneResponseDto creaPrenotazione(PrenotazioneRequestDto request) {
    // 1. Verifica slot disponibile
    SlotDisponibile slot = slotRepository.findById(request.getSlotId())
            .orElseThrow(() -> new ResourceNotFoundException("Slot non trovato"));
    
    if (slot.getPrenotazione() != null) {
        throw new ConflictException("Slot già prenotato");
    }
    
    // 2. Crea o recupera paziente
    Paziente paziente = pazienteService.findOrCreate(request.getPaziente());
    
    // 3. Crea prenotazione
    Prenotazione prenotazione = Prenotazione.builder()
            .paziente(paziente)
            .slot(slot)
            .stato(StatoPrenotazione.CONFERMATA)
            .dataPrenotazione(LocalDateTime.now())
            .build();
    
    // 4. Salva e ritorna
    return mapper.toResponseDto(repository.save(prenotazione));
}
```

---

## Gestione Errori

### Eccezioni Personalizzate

```java
// ResourceNotFoundException - per risorse non trovate
throw new ResourceNotFoundException("{Entità} non trovato con id: " + id);

// ConflictException - per conflitti di business
throw new ConflictException("Messaggio errore");

// ValidationException - per errori di validazione
throw new ValidationException("Campo non valido");
```

### Validazione Input

```java
public void validateRequest({Entità}RequestDto request) {
    if (request.getCampo() == null || request.getCampo().isBlank()) {
        throw new ValidationException("Il campo è obbligatorio");
    }
    // Altre validazioni...
}
```

---

## Best Practices

- ✅ Usa **constructor injection** con `@RequiredArgsConstructor`
- ✅ Usa `@Transactional` per le operazioni di scrittura
- ✅ Usa `@Transactional(readOnly = true)` per le operazioni di lettura
- ✅ Usa **DTO** per input/output, mai esporre direttamente le entity
- ✅ Valida sempre gli input prima di processare
- ✅ Lancia eccezioni specifiche per errori di business
- ✅ Usa il **Mapper** per convertire tra entity e DTO

---

## Output Atteso

Per ogni service, genera:

1. **Service** in `src/main/java/com/example/demo/service/{Entità}Service.java`
2. **Eventuali eccezioni custom** in `src/main/java/com/example/demo/exception/`
3. **Logica di business** completa e testabile

---

## Domande da Porre

Prima di procedere, chiedi:

1. Quali operazioni CRUD sono necessarie?
2. Qual è la logica di business specifica?
3. Quali validazioni sono richieste?
4. Quali eccezioni custom servono?
5. Ci sono dipendenze con altri service?