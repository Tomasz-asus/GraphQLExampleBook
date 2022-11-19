package com.example.grapqlexamplebook.controller;

import com.example.grapqlexamplebook.model.Author;
import com.example.grapqlexamplebook.model.Book;
import com.example.grapqlexamplebook.model.Rating;
import com.example.grapqlexamplebook.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@GraphQlTest(BookController.class)
class BookControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private BookRepository bookRepository;

    private final List<Book> books = new ArrayList<>();

    @Test
    public void contextLoads() {
        assertNotNull(graphQlTester);
        assertNotNull(bookRepository);
    }

    @BeforeEach
    void setUp() {
        var firstname1 = new Author(1,"firstName1","lastName1");
        var firstname2 = new Author(2,"firstName2","lastName2");
        var firstname3 = new Author(3,"firstName3","lastName3");
        books.add(new Book(1,"title1", 100, Rating.BEST, firstname1));
        books.add(new Book(2,"title2", 200, Rating.GOOD, firstname2));
        books.add(new Book(3,"title3", 300, Rating.NORMAL, firstname3));
    }

    @Test
    void testFindAllBooksQueryReturnsAllBooks() {
        String document = """
            query {
                allBooks {
                    id
                    title
                }
            }        
        """;

        when(bookRepository.findAll()).thenReturn(books);

        graphQlTester.document(document)
                .execute()
                .path("allBooks")
                .entityList(Book.class)
                .hasSize(3);
    }

}
