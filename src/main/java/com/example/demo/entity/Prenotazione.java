package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "PRENOTAZIONE")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_id_persona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "fk_id_medico")
    private Medico medico;

    private LocalDate data_visita;

    public Prenotazione() {
    }

    public Prenotazione(Persona persona, Medico medico, LocalDate data_visita) {
        this.persona = persona;
        this.medico = medico;
        this.data_visita = data_visita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDate getData_visita() {
        return data_visita;
    }

    public void setData_visita(LocalDate data_visita) {
        this.data_visita = data_visita;
    }
}