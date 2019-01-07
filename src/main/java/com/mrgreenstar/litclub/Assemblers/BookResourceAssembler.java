package com.mrgreenstar.litclub.Assemblers;

import com.mrgreenstar.litclub.Controllers.BookController;
import com.mrgreenstar.litclub.Entity.Book;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class BookResourceAssembler implements ResourceAssembler<Book, Resource<Book>> {
    @Override
    public Resource<Book> toResource(Book book) {
        return new Resource<>(book,
                linkTo(methodOn(BookController.class).findOneBook(book.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).findAllBooks()).withRel("books"));
    }
}
