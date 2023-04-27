package ru.averkiev.library.hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.averkiev.library.hibernate.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

}
