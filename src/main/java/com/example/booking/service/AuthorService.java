package com.example.booking.service;

import java.util.List;
import java.util.Optional;

import com.example.booking.domain.Author;

public interface AuthorService {
    List<Author> getAllAuthor();
    Optional<Author> getAuthorByName(String name);
    Optional<Author> getAuthorById(Long id);
    Author updateAuthor(Long id, Author author);
    void deleteAuthorById(Long id);
}
