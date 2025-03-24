package com.microservices.person_crud.repository;

import com.microservices.person_crud.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonProfileRepository extends MongoRepository<Person,String> {
    // Buscar persona por teléfono
    Optional<Person> findByCellphone(String cellphone);

    // Buscar personas por email
    List<Person> findByEmail(String email);

    //Buscar personas por nombre
    List<Person> findByName(String name);

    //Buscar personas por direccion
    List<Person> findByAddress(String address);
}
