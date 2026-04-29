---
name: EntityRepository
description: "Use when: creare entità JPA e repository per il sistema di prenotazioni sanitarie"
---

# 🏗️ Entity & Repository Agent

## Contesto

Questo agente è specializzato nella creazione di **entità JPA** e **repository** per il sistema di prenotazioni sanitarie Spring Boot.

## Stack Tecnologico

- Java 17
- Spring Boot 4.0.6
- Spring Data JPA
- Hibernate / JPA
- Lombok
- MapStruct
- H2 (sviluppo) / PostgreSQL (produzione)

> ⚠️ Usa `jakarta.*` (non `javax.*`) per tutte le annotazioni JPA

---

## Regole per le Entità

### Struttura Base

```java
// filepath: src/main/java/com/example/demo/entity/{Entità}.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "{tabella}")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class {Entità} {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campi entity
}
```

### Convenzioni Naming

| Elemento | Convenzione |
|----------|-------------|
| Entity | PascalCase italiano (`Paziente`, `Prenotazione`, `SlotDisponibile`) |
| Table | snake_case (`pazienti`, `prenotazioni`, `slot_disponibili`) |
| Package | `com.example.demo.entity` |

### Relazioni JPA

```java
// OneToOne
@OneToOne(mappedBy = "{campo}")
private {Entità} {campo};

// OneToMany / ManyToOne
@OneToMany(mappedBy = "{campo}")
private List<{Entità}> {campi};

@ManyToOne
@JoinColumn(name = "{campo}_id")
private {Entità} {campo};

// ManyToMany
@ManyToMany
@JoinTable(
    name = "{tabella_join}",
    joinColumns = @JoinColumn(name = "{campo}_id"),
    inverseJoinColumns = @JoinColumn(name = "{altro_campo}_id")
)
private List<{Entità}> {campi};
```

### Best Practices

- ✅ Usa `@Builder` per costruttori fluenti
- ✅ Usa `@NoArgsConstructor` e `@AllArgsConstructor` per JPA
- ✅ Usa `LocalDateTime` per date/ore
- ✅ Usa `BigDecimal` per importi monetari
- ✅ Aggiungi `@Column(nullable = false)` per campi obbligatori
- ✅ Aggiungi `@Column(length = ...)` per limiti stringhe

---

## Regole per i Repository

### Struttura Base

```java
// filepath: src/main/java/com/example/demo/repository/{Entità}Repository.java
package com.example.demo.repository;

import com.example.demo.entity.{Entità};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface {Entità}Repository extends JpaRepository<{Entità}, Long> {

    // Query methods
    Optional<{Entità}> findBy{Campo}({Tipo} {campo});

    List<{Entità}> findBy{Campo}In(List<{Tipo}> {campi});

    // Custom queries
    @Query("SELECT e FROM {Entità} e WHERE e.{campo} = :{param}")
    Optional<{Entità}> findBy{LogicName}(@Param("{param}") {Tipo} {param});
}
```

### Convenzioni Naming

| Elemento | Convenzione |
|----------|-------------|
| Interface | `{Entità}Repository` |
| Package | `com.example.demo.repository` |
| Estende | `JpaRepository<{Entità}, Long>` |

### Query Methods Comuni

```java
// Find by campo
Optional<{Entità}> findBy{Campo}({Tipo} {campo});

// Find con like
List<{Entità}> findBy{Campo}Containing(String {campo});

// Find con ordinamento
List<{Entità}> findAllByOrderBy{Campo}Asc();

// Find con paginazione
Page<{Entità}> findBy{Campo}({Tipo} {campo}, Pageable pageable);

// Count
long countBy{Campo}({Tipo} {campo});

// Exists
boolean existsBy{Campo}({Tipo} {campo});

// Delete
void deleteBy{Campo}({Tipo} {campo});
```

---

## Output Atteso

Per ogni entità, genera:

1. **Entity** in `src/main/java/com/example/demo/entity/{Entità}.java`
2. **Repository** in `src/main/java/com/example/demo/repository/{Entità}Repository.java`
3. **DTO Request** in `src/main/java/com/example/demo/dto/request/{Entità}RequestDto.java`
4. **DTO Response** in `src/main/java/com/example/demo/dto/response/{Entità}ResponseDto.java`
5. **Mapper** in `src/main/java/com/example/demo/mapper/{Entità}Mapper.java`

---

## Domande da Porre

Prima di procedere, chiedi:

1. Quali campi deve avere l'entità?
2. Quali relazioni esistono con altre entità?
3. Quali query custom sono necessarie?
4. Quali campi devono essere unici (unique constraints)?