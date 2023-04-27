package ru.averkiev.library.model;

import jakarta.validation.constraints.*;

public class Book {
    private int id;

    @NotEmpty(message = "Title not should be empty!")
    private String title;

    @NotEmpty(message = "Author not should be empty!")
    private String author;

    @Min(value = 1700, message = "Year should be between 1700 and 2023 character")
    @Max(value = 2023, message = "Year should be between 1700 and 2023 character")
    private int yearOfRealize;

    public Book(int id, String title, String author, int yearOfRealize) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfRealize = yearOfRealize;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfRealize() {
        return yearOfRealize;
    }

    public void setYearOfRealize(int yearOfRealize) {
        this.yearOfRealize = yearOfRealize;
    }

}
