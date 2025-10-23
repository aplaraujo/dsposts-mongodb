package com.example.dsposts_mongodb.config;

import com.example.dsposts_mongodb.models.entities.User;
import com.example.dsposts_mongodb.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test") // Configuração aplicada ao perfil de testes
public class TestConfig {

    @Autowired
    private UserRepository userRepository;

    // Carga inicial do banco de dados
    @PostConstruct
    public void init() {
        userRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Grey", "alex@gmail.com");
        User bob = new User(null, "Bob Green", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
    }
}
