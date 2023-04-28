package ru.averkiev.library.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.averkiev.library.hibernate.models.Book;
import ru.averkiev.library.hibernate.models.Person;
import ru.averkiev.library.hibernate.repositories.BooksRepository;
import ru.averkiev.library.hibernate.repositories.PeopleRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private static final int RENT_TIME = 1000 * 3600 * 24 * 10;  // 10 дней
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updateBook) {
        updateBook.setId(id);
        booksRepository.save(updateBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public List<Book> findByAbonent(Person abonent) {
        List<Book> books = booksRepository.findByAbonent(abonent);
        for (Book book : books) {
            if (book.getReceivedIn() != null && new Date().getTime() - book.getReceivedIn().getTime() < RENT_TIME) {
                System.out.println("книга не просрочена");
                book.setOverdue(false);
            }
            if (book.getReceivedIn() != null && new Date().getTime() - book.getReceivedIn().getTime() > RENT_TIME) {
                System.out.println("книга просрочена");
                book.setOverdue(true);
            }
        }
        return books;
    }

    @Transactional
    public void addBook(Person abonent, int bookId) {

        Book book = this.findById(bookId);

        book.setReceivedIn(new Date());
        abonent.setBooks(new ArrayList<>(Collections.singletonList(book)));
        book.setAbonent(abonent);

        booksRepository.save(book);
    }

    @Transactional
    public void rmBook(int bookId) {

        Book book = this.findById(bookId);
        Person abonent = book.getAbonent();
        book.setAbonent(null);
        book.setReceivedIn(null);

        abonent.getBooks().remove(book);

        booksRepository.save(book);
    }
}
