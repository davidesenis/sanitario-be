package com.example.demo.service;

import com.example.demo.dto.PrenotazioneDTO;
import com.example.demo.entity.Medico;
import com.example.demo.entity.Persona;
import com.example.demo.entity.Prenotazione;
import com.example.demo.mapper.PrenotazioneMapper;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.repository.PrenotazioneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final PersonaRepository personaRepository;
    private final MedicoRepository medicoRepository;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository, 
                                PersonaRepository personaRepository,
                                MedicoRepository medicoRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.personaRepository = personaRepository;
        this.medicoRepository = medicoRepository;
    }

    public List<PrenotazioneDTO> findAll() {
        return prenotazioneRepository.findAll().stream()
                .map(PrenotazioneMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<PrenotazioneDTO> findById(Long id) {
        return prenotazioneRepository.findById(id)
                .map(PrenotazioneMapper::toDTO);
    }

    public PrenotazioneDTO save(PrenotazioneDTO prenotazioneDTO) {
        Prenotazione prenotazione = PrenotazioneMapper.toEntity(prenotazioneDTO);
        
        // Imposta le relazioni
        if (prenotazioneDTO.getIdPersona() != null) {
            Optional<Persona> persona = personaRepository.findById(prenotazioneDTO.getIdPersona());
            persona.ifPresent(prenotazione::setPersona);
        }
        if (prenotazioneDTO.getIdMedico() != null) {
            Optional<Medico> medico = medicoRepository.findById(prenotazioneDTO.getIdMedico());
            medico.ifPresent(prenotazione::setMedico);
        }
        
        Prenotazione saved = prenotazioneRepository.save(prenotazione);
        return PrenotazioneMapper.toDTO(saved);
    }

    public void deleteById(Long id) {
        prenotazioneRepository.deleteById(id);
    }
}