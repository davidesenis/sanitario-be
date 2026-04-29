package com.example.demo.dto;

import java.time.LocalDate;

public class PrenotazioneDTO {
    private Long id;
    private Long idPersona;
    private Long idMedico;
    private LocalDate data_visita;

    public PrenotazioneDTO() {
    }

    public PrenotazioneDTO(Long id, Long idPersona, Long idMedico, LocalDate data_visita) {
        this.id = id;
        this.idPersona = idPersona;
        this.idMedico = idMedico;
        this.data_visita = data_visita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public LocalDate getData_visita() {
        return data_visita;
    }

    public void setData_visita(LocalDate data_visita) {
        this.data_visita = data_visita;
    }
}