package ru.averkiev.library.hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.averkiev.library.hibernate.models.Book;
import ru.averkiev.library.hibernate.models.Person;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAbonent(Person abonent);
    List<Book> findByTitleStartingWith(String startTitle);
}