package com.example.demo.mapper;

import com.example.demo.dto.MedicoDTO;
import com.example.demo.entity.Medico;

public class MedicoMapper {
    
    public static MedicoDTO toDTO(Medico medico) {
        if (medico == null) {
            return null;
        }
        return new MedicoDTO(
            medico.getId(),
            medico.getNome(),
            medico.getCognome(),
            medico.getEmail(),
            medico.getReparto()
        );
    }

    public static Medico toEntity(MedicoDTO dto) {
        if (dto == null) {
            return null;
        }
        Medico medico = new Medico();
        medico.setId(dto.getId());
        medico.setNome(dto.getNome());
        medico.setCognome(dto.getCognome());
        medico.setEmail(dto.getEmail());
        medico.setReparto(dto.getReparto());
        return medico;
    }
}