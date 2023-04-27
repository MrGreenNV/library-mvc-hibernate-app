package ru.averkiev.library.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.averkiev.library.model.Book;
import ru.averkiev.library.model.Person;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, yearofrealize) VALUES (?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYearOfRealize());
    }


    public void update(Book updateBook, int id) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, yearofrealize=? WHERE id=?",
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getYearOfRealize(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }


    public Person isFreeBook(int id) {
        return jdbcTemplate.query("SELECT Person.id, fullName, yearOfBirthday FROM Person JOIN Book on Person.id=person_id WHERE Book.id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void addBook(int personId, int bookId) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", personId, bookId);
    }

    public void rmBook(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE id=?", id);
    }
}
