package com.microservices.person_crud.service;

import com.microservices.person_crud.model.Person;
import com.microservices.person_crud.repository.PersonProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PersonProfileService {
    @Autowired
    private PersonProfileRepository personRepository;

    // Obtener todas las personas
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // Buscar persona por id
    public Optional<Person> getPersonById(String id) {
        return personRepository.findById(id);
    }

    // Buscar persona por teléfono
    public Optional<Person> findByCellphone(String cellphone) {
        return personRepository.findByCellphone(cellphone);
    }

    // Buscar personas por email
    public List<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    // Buscar personas por nombre
    public List<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    // Buscar personas por direccion
    public List<Person> findByAddress (String address){ return personRepository.findByAddress(address); }

    // Buscar Personas por cualquier parametro
    public List<Person> searchPersons(String email, String name, String address) {
        if (email != null) return findByEmail(email);
        if (name != null) return findByName(name);
        if (address != null) return findByAddress(address);
        return Collections.emptyList();
    }
}
