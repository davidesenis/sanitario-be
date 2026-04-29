package com.example.demo.service;

import com.example.demo.dto.MedicoDTO;
import com.example.demo.entity.Medico;
import com.example.demo.mapper.MedicoMapper;
import com.example.demo.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<MedicoDTO> findAll() {
        return medicoRepository.findAll().stream()
                .map(MedicoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<MedicoDTO> findById(Long id) {
        return medicoRepository.findById(id)
                .map(MedicoMapper::toDTO);
    }

    public MedicoDTO save(MedicoDTO medicoDTO) {
        Medico medico = MedicoMapper.toEntity(medicoDTO);
        Medico saved = medicoRepository.save(medico);
        return MedicoMapper.toDTO(saved);
    }

    public void deleteById(Long id) {
        medicoRepository.deleteById(id);
    }
}