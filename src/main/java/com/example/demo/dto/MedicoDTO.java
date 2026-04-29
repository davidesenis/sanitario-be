package com.example.demo.dto;

public class MedicoDTO {
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String reparto;

    public MedicoDTO() {
    }

    public MedicoDTO(Long id, String nome, String cognome, String email, String reparto) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.reparto = reparto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String reparto) {
        this.reparto = reparto;
    }
}