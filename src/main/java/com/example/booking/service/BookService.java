package com.example.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.booking.domain.Book;

@Service
public interface BookService {
    List<Book> getAllBook();
    Optional<Book> getBookByTitle(String title);
    Book saveBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBookByTitle(String title);
    void deleteBookById(Long id);
}
