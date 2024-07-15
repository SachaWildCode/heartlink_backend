package fr.slghive.heartlink.config;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.slghive.heartlink.entities.TypeEntity;
import fr.slghive.heartlink.repositories.TypeRepository;

@Configuration
public class DefaultTypeInitializer {

    @Bean
    public ApplicationRunner initializeDefaultTypes(TypeRepository typeRepository) {
        return args -> {
            List<String> defaultTypes = List.of("Humanitarian", "Medical", "Religious", "Financial");

            for (String typeName : defaultTypes) {
                if (!typeRepository.existsByName(typeName)) {
                    TypeEntity type = new TypeEntity();
                    type.setName(typeName);
                    typeRepository.save(type);
                }
            }
        };
    }
}