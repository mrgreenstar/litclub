package com.mrgreenstar.litclub.Validators;

import com.mrgreenstar.litclub.Entity.Author;
import com.mrgreenstar.litclub.Entity.Book;
import com.mrgreenstar.litclub.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    private BookRepository bookRepository;

    @Autowired
    public BookValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Book book = (Book) obj;
        if (bookRepository.existsByTitleAndGenre(book.getTitle(), book.getGenre())) {
            errors.rejectValue("title", "book.alreadyExists");
        }
    }
}
