package com.example.grapqlexamplebook.repository;

import com.example.grapqlexamplebook.model.Book;
import com.example.grapqlexamplebook.model.Rating;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    private final AuthorRepository authorRepository;
    private List<Book> books = new ArrayList<>();

    public BookRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findOne(Integer id) {
        return books.stream().filter(book -> book.id() == id).findFirst().orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @PostConstruct
    private void init() {
        books.add(new Book(1,"title1", 100, Rating.BEST, authorRepository.findByName("firstName1 lastName1")));
        books.add(new Book(2,"title2", 200, Rating.GOOD, authorRepository.findByName("firstName2 lastName2")));
        books.add(new Book(3,"title3", 300, Rating.NORMAL, authorRepository.findByName("firstName3 lastName3")));
    }

}
