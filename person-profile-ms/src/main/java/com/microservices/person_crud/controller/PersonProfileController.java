package com.microservices.person_crud.controller;

import com.microservices.person_crud.model.Person;
import com.microservices.person_crud.service.PersonProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile/get-profile")
public class PersonProfileController {

    @Autowired
    private PersonProfileService personService;

    // Obtener todas las personas
    @GetMapping()
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }

    // Buscar persona por ID
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable String id) {
        return personService.getPersonById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar personas por email, nombre o dirección usando Query Params
    @GetMapping("/search")
    public ResponseEntity<List<Person>> searchPersons(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address) {

        List<Person> persons = personService.searchPersons(email, name, address);
        return persons.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(persons);
    }
}
