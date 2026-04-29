package com.example.demo.config;

import com.example.demo.entity.Medico;
import com.example.demo.repository.MedicoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(MedicoRepository medicoRepository) {
        return args -> {
            if (medicoRepository.count() == 0) {
                medicoRepository.save(new Medico("Mario", "Rossi", "mario.rossi@sanitario.it", "Cardiologia"));
                medicoRepository.save(new Medico("Giulia", "Bianchi", "giulia.bianchi@sanitario.it", "Dermatologia"));
                medicoRepository.save(new Medico("Luca", "Verdi", "luca.verdi@sanitario.it", "Ortopedia"));
            }
        };
    }
}