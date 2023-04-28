package ru.averkiev.library.hibernate.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.averkiev.library.hibernate.models.Book;
import ru.averkiev.library.hibernate.models.Person;
import ru.averkiev.library.hibernate.services.BooksService;
import ru.averkiev.library.hibernate.services.PeopleService;
import ru.averkiev.library.hibernate.util.BookValidator;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;
    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService, BookValidator bookValidator) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@ModelAttribute("person") Person person, @PathVariable("id") int id, Model model) {

        Book book = booksService.findById(id);
        Person abonent = book.getAbonent();

        model.addAttribute("book", book);

        if (abonent == null) {
            model.addAttribute("people", peopleService.findAll());
        } else {
            model.addAttribute("abonent", abonent);
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);

        if(bindingResult.hasErrors()) {
            return "books/new";
        }

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/add")
    public String addBook(@ModelAttribute("person") Person person, @PathVariable("id") int bookId) {
        booksService.addBook(person, bookId);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/rm")
    public String rmBook(@PathVariable("id") int bookId) {
        booksService.rmBook(bookId);
        return "redirect:/books";
    }
}