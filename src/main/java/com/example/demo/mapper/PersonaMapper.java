package com.example.demo.mapper;

import com.example.demo.dto.PersonaDTO;
import com.example.demo.entity.Persona;

public class PersonaMapper {
    
    public static PersonaDTO toDTO(Persona persona) {
        if (persona == null) {
            return null;
        }
        return new PersonaDTO(
            persona.getId(),
            persona.getNome(),
            persona.getCognome(),
            persona.getEmail()
        );
    }

    public static Persona toEntity(PersonaDTO dto) {
        if (dto == null) {
            return null;
        }
        Persona persona = new Persona();
        persona.setId(dto.getId());
        persona.setNome(dto.getNome());
        persona.setCognome(dto.getCognome());
        persona.setEmail(dto.getEmail());
        return persona;
    }
}