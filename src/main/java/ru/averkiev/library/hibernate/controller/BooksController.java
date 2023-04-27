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
import ru.averkiev.library.hibernate.util.BookValidator;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BooksService booksService, BookValidator bookValidator) {
        this.booksService = booksService;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@ModelAttribute("person") Person person, @PathVariable("id") int id, Model bookModel, Model personModel, Model peopleModel) {
        bookModel.addAttribute("book", booksService.findById(id));
//        personModel.addAttribute("personIs", bookDAO.isFreeBook(id));
//        peopleModel.addAttribute("people", bookDAO.getPeople());
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
//        bookDAO.addBook(person.getId(), bookId);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/rm")
    public String rmBook(@PathVariable("id") int bookId) {
//        bookDAO.rmBook(bookId);
        return "redirect:/books";
    }
}