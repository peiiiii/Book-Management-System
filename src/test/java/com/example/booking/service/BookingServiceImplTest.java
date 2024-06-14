package com.example.booking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.booking.domain.Author;
import com.example.booking.domain.Book;
import com.example.booking.repository.AuthorRepository;
import com.example.booking.repository.BookRepository;
import com.example.booking.service.impl.BookServiceImpl;

class BookingServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveBook() {
        Book book = new Book();
        book.setTitle("Test");
        book.setPublicationDate(LocalDate.of(2024, 6, 10));

        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        Set<Author> authors = new HashSet<>();
        authors.add(author);

        book.setAuthors(authors);

        when(authorRepository.findByFirstNameAndLastName("John", "Doe")).thenReturn(Optional.of(author));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book savedBook = bookService.saveBook(book);

        assertNotNull(savedBook);
        assertEquals("Test", savedBook.getTitle());
        verify(bookRepository, times(1)).save(book);
    }
}