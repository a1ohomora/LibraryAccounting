package ru.rozvezev.libraryaccounting.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @NotNull
    @Size(min = 1, max = 100, message = "Name length should be between 1 and 100")
    @Column(name = "name")
    private String name;
    @NotNull
    @Size(min = 1, max = 100, message = "Name length should be between 1 and 100")
    @Column(name = "author")
    private String author;

    @Column(name = "year")
    private int year;

    @Column(name = "taken_at")
    private LocalDate takenAt;

    @Transient
    private boolean expired;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person owner;

    public Book() {

    }

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    @PostLoad
    private void evaluateExpired(){
        if (Objects.isNull(takenAt)) return;
        LocalDate now = LocalDate.now();
        expired = takenAt.datesUntil(now).count() > 10L;
    }


    /* Setters and getters*/

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public LocalDate getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(LocalDate takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
