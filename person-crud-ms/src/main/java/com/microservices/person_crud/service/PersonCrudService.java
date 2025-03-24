package com.microservices.person_crud.service;

import com.microservices.person_crud.model.Person;
import com.microservices.person_crud.repository.PersonCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonCrudService {
    @Autowired
    private PersonCrudRepository personRepository;

    @Autowired
    private JwtTokenService jwtTokenService;

    // Crear una persona y generar un token
    public String createPerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return jwtTokenService.generateToken(); // Devolver solo el token
    }

    /// Eliminar una persona (validar el token antes de eliminar)
    public void deletePerson(String id, String token) {
        // Validar el token usando JwtTokenService
        if (!jwtTokenService.validateToken(token)) {
            throw new SecurityException("Invalid token"); // Lanzar una excepción si el token no es válido
        }

        // Si el token es válido, eliminar la persona
        personRepository.deleteById(id);
    }
}
