package com.mrgreenstar.litclub.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToMany(mappedBy="books")
    private List<Author> authors;

    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    private String description;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="book_genre", joinColumns=@JoinColumn(name="genre_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name = "book_id",
                    referencedColumnName = "id"))
    private List<Genre> genres;

    private Double rating;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;

    public Book() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", publicationDate=" + publicationDate +
                ", description='" + description + '\'' +
                ", genres=" + genres +
                ", rating=" + rating +
                ", reviews=" + reviews +
                '}';
    }
}
