package com.example.grapqlexamplebook.repository;

import com.example.grapqlexamplebook.model.Author;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {

    private List<Author> authors = new ArrayList<>();

    public List<Author> findAll() {
        return authors;
    }

    public Author findById(int id) {
        return authors.stream()
                .filter(author -> author.id() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Author with this id not found"));
    }

    public Author findByName(String name) {
        return authors.stream()
                .filter(author -> author.fullName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Author with this name not found"));
    }

    @PostConstruct
    private void init() {
        authors.add(new Author(1,"firstName1","lastName1"));
        authors.add(new Author(2,"firstName2","lastName2"));
        authors.add(new Author(3,"firstName3","lastName3"));
    }
}
