package com.microservices.person_crud.controller;

import com.microservices.person_crud.model.Person;
import com.microservices.person_crud.service.PersonCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class PersonCrudController {

    @Autowired
    private PersonCrudService personService;

    // Crear una persona
    @PostMapping("/create-profile")
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        String token = personService.createPerson(person); // Obtener el token
        return ResponseEntity.ok(token); // Devolver el token en la respuesta
    }

    // Eliminar una persona (protegido por token)
    @DeleteMapping("/delete-profile/{id}")
    public ResponseEntity<Void> deletePerson(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String id) {

        try {
            String token = authHeader.replace("Bearer ", ""); // Eliminar el prefijo "Bearer "
            personService.deletePerson(id, token);
            return ResponseEntity.noContent().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Iterable<Person>> getAllPersons(
            @RequestHeader("Authorization") String authHeader,
            @RequestHeader("X-API-KEY") String apiKey) {

        try {
            String token = authHeader.replace("Bearer ", ""); // Extraer el token del header
            Iterable<Person> persons = personService.getAllPersons(token, apiKey);
            return ResponseEntity.ok(persons);
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
