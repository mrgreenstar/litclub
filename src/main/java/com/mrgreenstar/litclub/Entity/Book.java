package com.mrgreenstar.litclub.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(mappedBy="books")
    private List<Author> authors;

    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    private String description;

    @ManyToOne
    @JoinColumn(name="genre_id")
    private Genre genre;

    private Double rating;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;

    public Book() {}

    public Book(String title, List<Author> authors, Date publicationDate,
                String description, Genre genre, List<Review> reviews) {
        this.title = title;
        this.authors = authors;
        this.publicationDate = publicationDate;
        this.description = description;
        this.genre = genre;
        // Here 200.0 is start rating for each book.
        this.rating = 200.0;
        this.reviews = reviews;
    }

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

    public Genre getGenres() {
        return genre;
    }

    public void setGenres(Genre genres) {
        this.genre = genres;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setDefaultRating() {
        this.rating = 200.0;
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
                ", genre=" + genre +
                ", rating=" + rating +
                ", reviews=" + reviews +
                '}';
    }
}
