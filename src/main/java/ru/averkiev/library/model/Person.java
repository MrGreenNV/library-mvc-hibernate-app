package ru.averkiev.library.model;

import jakarta.validation.constraints.*;

import java.util.List;

public class Person {
    private int id;

    @Pattern(regexp = "([A-Z][a-z]+|[А-Я][а-я]+) ([A-Z][a-z]+|[А-Я][а-я]+) ([A-Z][a-z]+|[А-Я][а-я]+)", message = "The FullName should be like this: Lastname Firstname Patronymic")
    @Size(min = 8, max = 100, message = "The Fullname should be fuller")
    @NotEmpty(message = "The FullName not should be empty")
    private String fullName;

    @Min(value = 1900, message = "Year should be between 1900 and 2017 character")
    @Max(value = 2017, message = "Year should be between 1900 and 2017 character")
    private int yearOfBirthday;

    private List<Book> listBooks = null;

    public Person(int id, String fullName, int date) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirthday = date;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirthday() {
        return yearOfBirthday;
    }

    public void setYearOfBirthday(int yearOfBirthday) {
        this.yearOfBirthday = yearOfBirthday;
    }

    public List<Book> getListBooks() {
        return listBooks;
    }

    public void setListBooks(List<Book> listBooks) {
        this.listBooks = listBooks;
    }
}
