package ru.averkiev.library.hibernate.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Title not should be empty!")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Author not should be empty!")
    @Column(name = "author")
    private String author;

    @Min(value = 1700, message = "Year should be between 1700 and 2023 character")
    @Max(value = 2023, message = "Year should be between 1700 and 2023 character")
    @Column(name = "yearofrealize")
    private int yearOfRealize;

    @Column(name = "received_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receivedIn;

    @ManyToOne()
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person abonent;

    @Transient
    private boolean isOverdue;

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

    public Date getReceivedIn() {
        return receivedIn;
    }

    public void setReceivedIn(Date receivedIn) {
        this.receivedIn = receivedIn;
    }

    public Person getAbonent() {
        return abonent;
    }

    public void setAbonent(Person abonent) {
        this.abonent = abonent;
    }

    public boolean isOverdue() {
        return isOverdue;
    }

    public void setOverdue(boolean overdue) {
        isOverdue = overdue;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
