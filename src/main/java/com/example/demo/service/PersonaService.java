package com.example.demo.service;

import com.example.demo.dto.PersonaDTO;
import com.example.demo.entity.Persona;
import com.example.demo.mapper.PersonaMapper;
import com.example.demo.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<PersonaDTO> findAll() {
        return personaRepository.findAll().stream()
                .map(PersonaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<PersonaDTO> findById(Long id) {
        return personaRepository.findById(id)
                .map(PersonaMapper::toDTO);
    }

    public PersonaDTO save(PersonaDTO personaDTO) {
        Persona persona = PersonaMapper.toEntity(personaDTO);
        Persona saved = personaRepository.save(persona);
        return PersonaMapper.toDTO(saved);
    }

    public void deleteById(Long id) {
        personaRepository.deleteById(id);
    }
}