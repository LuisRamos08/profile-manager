package com.microservices.person_crud.repository;

import com.microservices.person_crud.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonCrudRepository extends MongoRepository<Person,String> {
}
