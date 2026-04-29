# 🎯 Orchestratore — Sistema di Prenotazioni Sanitarie

## Contesto del Progetto

Applicazione backend **Spring Boot 4.0.6 / Java 17** per la **gestione delle prenotazioni in ambito sanitario**.

### Stato attuale
- API REST con Swagger/OpenAPI
- Nessun sistema di autenticazione (accesso libero)
- I pazienti invieranno i propri dati tramite form (implementazione futura)

### Evoluzione pianificata
- **Fase 2**: Form pubblico di prenotazione

Tieni conto di questa roadmap quando progetti le entità e le API: **non implementare ora ciò che appartiene alle fasi future, ma non ostacolarle con scelte architetturali sbagliate**.

---

## Stack Tecnologico

```
Java 17
Spring Boot 4.0.6
Spring Web MVC
Spring Data JPA
Springdoc OpenAPI (Swagger UI)
Hibernate / JPA
Maven
Lombok
MapStruct
H2 (sviluppo) / PostgreSQL (produzione)
JUnit 5 + Mockito (test)
```

> ⚠️ Spring Boot 4.x usa `jakarta.*` (non `javax.*`) per tutte le annotazioni JPA, Servlet e Validation.

---

## Dominio Applicativo

Le entità principali del sistema di prenotazioni sanitarie sono:

```
Paziente         → chi prenota (nome, cognome, CF, data di nascita, contatto)
SlotDisponibile  → una fascia oraria disponibile per un medico
```

### Relazioni
```
SlotDisponibile ──< Prenotazione  (uno slot può avere una prenotazione — uno a uno)
Paziente ──< Prenotazione    (un paziente può avere più prenotazioni)
```

---

## Agenti Disponibili

| Agente | File | Quando usarlo |
|--------|------|---------------|
| Entity & Repository | `entity-repository.md` | Creare entità JPA e repository |
| Service Layer | `service-layer.md` | Logica di business e gestione prenotazioni |
| Controller | `controller.md` | Esporre endpoint REST con Swagger |

---

## Struttura del Progetto

```
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── config/
│   │   │   └── OpenApiConfig.java
│   │   ├── controller/
│   │   │   ├── PrenotazioneController.java
│   │   │   ├── PazienteController.java
│   │   │   └── SlotController.java
│   │   ├── service/
│   │   │   ├── PrenotazioneService.java
│   │   │   ├── PazienteService.java
│   │   │   └── SlotService.java
│   │   ├── repository/
│   │   │   ├── PrenotazioneRepository.java
│   │   │   ├── PazienteRepository.java
│   │   │   └── SlotRepository.java
│   │   ├── entity/
│   │   │   ├── Prenotazione.java
│   │   │   ├── Paziente.java
│   │   │   └── SlotDisponibile.java
│   │   ├── dto/
│   │   │   ├── request/
│   │   │   └── response/
│   │   ├── mapper/
│   │   ├── exception/
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   ├── ResourceNotFoundException.java
│   │   │   └── ErrorResponse.java
│   │   └── DemoApplication.java
│   └── resources/
│       ├── application.yml
│       └── application-dev.yml
└── test/
```

---

## Ordine di Sviluppo Consigliato

```
1. Entity & Repository  → definisci il modello dati
2. Service Layer        → implementa la logica di prenotazione
3. Controller           → esponi le API
```

---

## Convenzioni

### Naming
```
Entità      → PascalCase italiano (Paziente, Prenotazione, Medico, SlotDisponibile)
Controller  → {Entità}Controller
Service     → {Entità}Service
Repository  → {Entità}Repository
DTO request → {Entità}RequestDto
DTO response→ {Entità}ResponseDto
Mapper      → {Entità}Mapper
```

### URL REST
```
GET    /api/v1/{risorse}         → lista (con filtri opzionali)
GET    /api/v1/{risorse}/{id}    → singolo elemento
POST   /api/v1/{risorse}         → creazione
PUT    /api/v1/{risorse}/{id}    → aggiornamento
DELETE /api/v1/{risorse}/{id}    → eliminazione
```

### HTTP Status
```
200 OK          → GET, PUT success
201 Created     → POST success
204 No Content  → DELETE success
400 Bad Request → validazione fallita
404 Not Found   → risorsa non trovata
409 Conflict    → conflitto (es: slot già prenotato)
500 Server Error→ errore generico
```

---

## Regole Generali

- ✅ Usa **Lombok** per eliminare boilerplate
- ✅ Usa **DTO separati** dall'entity (mai esporre l'entity direttamente nelle API)
- ✅ Documenta **ogni endpoint** con `@Operation` e `@ApiResponse`
- ✅ Valida sempre i dati in input con `@Valid`
- ✅ Gestisci le eccezioni con `GlobalExceptionHandler`
- ✅ Usa **constructor injection** (mai `@Autowired` su campo)
- ✅ Usa `jakarta.*` per tutte le annotazioni (Spring Boot 4.x)
- ❌ Non implementare autenticazione/login (è nella Fase 3)
- ❌ Non esporre stack trace nelle risposte di errore

---

## 🛠️ Gestione Agenti

Questa sezione documenta come creare, modificare e gestire agenti personalizzati nel progetto.

### Struttura File Agente

Ogni agente deve essere un file `.agent.md` nella cartella `.github/agents/`.

```
.github/agents/
├── Orchestratore.agent.md    # Agente principale (questo file)
├── NuovoAgente.agent.md      # Esempio di nuovo agente
└── ...
```

### Come Creare un Nuovo Agente

1. **Crea il file** in `.github/agents/{NomeAgente}.agent.md`
2. **Usa il frontmatter YAML** obbligatorio:

```yaml
---
name: NomeAgente
description: "Breve descrizione che spiega quando usare questo agente. Usa il pattern 'Use when: ...'"
---
```

3. **Struttura consigliata** del body:
   - Contesto del progetto
   - Stack tecnologico
   - Regole specifiche dell'agente
   - Esempi di utilizzo

### Frontmatter Obbligatorio

| Campo | Descrizione | Esempio |
|-------|-------------|---------|
| `name` | Nome dell'agente (deve corrispondere al nome del file) | `EntityRepository` |
| `description` | Descrizione che attiva l'agente. Usa "Use when:" per chiarezza | `"Use when: creare entità JPA e repository"` |

> ⚠️ **Regole importanti**:
> - Il `name` deve essere esattamente uguale al nome del file (senza `.agent.md`)
> - La `description` deve contenere le parole chiave che attivano l'agente
> - Non usare due punti non escapati nelle description (usa le virgolette)

### Come Modificare un Agente Esistente

1. Leggi il file corrente per comprendere la struttura
2. Modifica solo le sezioni necessarie
3. Mantieni il frontmatter invariato
4. Valida che le modifiche siano coerenti con il progetto

### Best Practices per gli Agenti

- **Un agente per responsabilità**: ogni agente dovrebbe gestire un dominio specifico
- **Descrizioni chiare**: usa frasi che descrivono esattamente quando invocare l'agente
- **Documentazione minima**: spiega il contesto, non ogni dettaglio tecnico
- **Coerenza con il progetto**: mantieni allineate le informazioni (stack, convenzioni, ecc.)

### Esempio: Nuovo Agente

```yaml
---
name: TestGenerator
description: "Use when: generare test unitari o di integrazione per il progetto Spring Boot"
---

# 🧪 Test Generator

## Contesto

Genera test JUnit 5 per il progetto sanitario.

## Regole

- Usa **Mockito** per i mock
- Segui la convenzione `*Test.java` per i nomi dei file
- Posiziona i test in `src/test/java/`
- Copia la struttura dei package dal codice sorgente
```

---

## 🔄 Auto-Miglioramento

Questo agente può essere modificato per migliorare le proprie capacità. Quando noti opportunità di miglioramento:

1. **Aggiorna la documentazione** se le istruzioni non sono chiare
2. **Aggiungi nuove sezioni** se emergono nuovi requisiti
3. **Migliora le descrizioni** per attivazioni più precise
4. **Corregge errori** o imprecisioni nelle istruzioni

### Criteri di Miglioramento

- ✅ Aggiungere esempi concreti quando utili
- ✅ Precisare meglio le condizioni di attivazione
- ✅ Integrare nuove best practice scoperte
- ❌ Non rimuovere informazioni essenziali senza motivo
- ❌ Non cambiare il focus principale dell'agente