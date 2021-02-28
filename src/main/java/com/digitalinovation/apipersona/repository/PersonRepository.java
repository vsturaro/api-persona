package com.digitalinovation.apipersona.repository;

import com.digitalinovation.apipersona.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
