package com.microservices.person_crud.service;

import com.microservices.person_crud.model.Person;
import com.microservices.person_crud.repository.PersonProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PersonProfileService {
    @Autowired
    private PersonProfileRepository personRepository;

    // Obtener todas las personas
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String API_KEY = "super-secret-key"; // Reemplazar con el real
    private static final String PERSON_CRUD_URL = "http://person-crud-ms/profile/get-all";

    public List<Person> getAllPersons() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getJwtToken());
        headers.set("X-API-KEY", API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<Person[]> response = restTemplate.exchange(
                PERSON_CRUD_URL, HttpMethod.GET, request, Person[].class);

        return Arrays.asList(response.getBody());
    }

    private String getJwtToken() {
        ResponseEntity<String> response = restTemplate.getForEntity(PERSON_CRUD_URL, String.class);
        return response.getBody(); // Devuelve el token recibido
    }
//
//    // Buscar persona por id
//    public Optional<Person> getPersonById(String id) {
//        return personRepository.findById(id);
//    }
//
//    // Buscar persona por teléfono
//    public Optional<Person> findByCellphone(String cellphone) {
//        return personRepository.findByCellphone(cellphone);
//    }
//
//    // Buscar personas por email
//    public List<Person> findByEmail(String email) {
//        return personRepository.findByEmail(email);
//    }
//
//    // Buscar personas por nombre
//    public List<Person> findByName(String name) {
//        return personRepository.findByName(name);
//    }
//
//    // Buscar personas por direccion
//    public List<Person> findByAddress (String address){ return personRepository.findByAddress(address); }
//
//    // Buscar Personas por cualquier parametro
//    public List<Person> searchPersons(String email, String name, String address) {
//        if (email != null) return findByEmail(email);
//        if (name != null) return findByName(name);
//        if (address != null) return findByAddress(address);
//        return Collections.emptyList();
//    }
}
