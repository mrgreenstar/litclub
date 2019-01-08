package com.mrgreenstar.litclub.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    private String genreName;

    @OneToMany(mappedBy = "genre")
    private List<Book> books;

    public Genre() {
    }

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + Id +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
