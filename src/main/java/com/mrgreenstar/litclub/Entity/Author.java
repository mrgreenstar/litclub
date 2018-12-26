package com.mrgreenstar.litclub.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    private String biography;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="author_book", joinColumns=@JoinColumn(name="book_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name = "author_id",
                    referencedColumnName = "id"))
    private List<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", biography='" + biography + '\'' +
                ", books=" + books +
                '}';
    }
}
