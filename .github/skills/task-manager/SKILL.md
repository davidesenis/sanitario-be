# Task Manager Skill

Questa skill permette di gestire le task provenienti dalle issue GitHub.

## Attivazione

Usa questa skill quando l'utente chiede di:
- "Svolgi task #n"
- "Gestisci issue #n"
- "Esegui task numero n"

## Funzionalità

1. **Lettura Issue**: Recupera il contenuto dell'issue da GitHub
2. **Analisi**: Determina quale agente coinvolgere in base al contenuto
3. **Esecuzione**: Delega al agente appropriato
4. **Storico**: Aggiorna il file `.github/tasks/STORICO.md`

## Agenti Disponibili

- `EntityRepository` - Per entità JPA, repository, DTO
- `ServiceLayer` - Per logica di business
- `Controller` - Per API REST e Swagger

## Utilizzo

L'agente `TaskManager` viene automaticamente attivato quando l'utente richiede di eseguire un task.