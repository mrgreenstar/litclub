package com.mrgreenstar.litclub.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    private String firstName;
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Temporal(TemporalType.DATE)
    private Date deathDate;

    private String biography;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="author_book", joinColumns=@JoinColumn(name="author_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="book_id",
                    referencedColumnName = "id"))
    private Set<Book> books;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
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

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", deathDate=" + deathDate +
                ", biography='" + biography + '\'' +
                ", books=" + books +
                '}';
    }
}
