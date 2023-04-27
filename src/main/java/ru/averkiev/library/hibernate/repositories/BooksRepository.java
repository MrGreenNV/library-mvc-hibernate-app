package ru.averkiev.library.hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.averkiev.library.hibernate.models.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

}
