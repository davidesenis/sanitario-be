package com.example.demo.mapper;

import com.example.demo.dto.PrenotazioneDTO;
import com.example.demo.entity.Prenotazione;

public class PrenotazioneMapper {
    
    public static PrenotazioneDTO toDTO(Prenotazione prenotazione) {
        if (prenotazione == null) {
            return null;
        }
        Long idPersona = prenotazione.getPersona() != null ? prenotazione.getPersona().getId() : null;
        Long idMedico = prenotazione.getMedico() != null ? prenotazione.getMedico().getId() : null;
        
        return new PrenotazioneDTO(
            prenotazione.getId(),
            idPersona,
            idMedico,
            prenotazione.getData_visita()
        );
    }

    public static Prenotazione toEntity(PrenotazioneDTO dto) {
        if (dto == null) {
            return null;
        }
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setId(dto.getId());
        prenotazione.setData_visita(dto.getData_visita());
        return prenotazione;
    }
}