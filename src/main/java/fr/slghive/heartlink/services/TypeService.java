package fr.slghive.heartlink.services;

import org.springframework.stereotype.Service;

import fr.slghive.heartlink.repositories.TypeRepository;

@Service
public class TypeService {
    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

}
