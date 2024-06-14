package com.example.booking.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.booking.domain.Author;
import com.example.booking.domain.Book;
import com.example.booking.repository.AuthorRepository;
import com.example.booking.repository.BookRepository;
import com.example.booking.service.BookService;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository; 
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Book> getBookByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book saveBook(Book book){
        Set<Author> authors = new HashSet<>();
        for (Author author : book.getAuthors()) {
            System.out.println(author);
            Optional<Author> existingAuthor = authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
            if (existingAuthor.isPresent()) {
                authors.add(existingAuthor.get());
            } else {
                authors.add(author);
            }
        }
        book.setAuthors(authors);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBookByTitle(String title){

        Optional<Book> book = bookRepository.findByTitle(title);
        if (book.isPresent()) {
            Book b = book.get();
            Set<Author> authors = b.getAuthors();
            bookRepository.delete(b);
            for (Author author : authors) {
                if (author.getBooks().size() == 1) { 
                    authorRepository.delete(author);
                }
            }
        }

    }
    
    @Override
    @Transactional
    public void deleteBookById(Long id){

        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Book b = book.get();
            Set<Author> authors = b.getAuthors();
            bookRepository.delete(b);
            for (Author author : authors) {
                if (author.getBooks().size() == 1) { 
                    authorRepository.delete(author);
                }
            }
        }
    }

    @Override
    public Book updateBook(Long id, Book book){

        Book b = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        System.out.println(b);
        b.setTitle(book.getTitle());
        b.setAuthors(book.getAuthors());
        b.setPublicationDate(book.getPublicationDate());

        return bookRepository.save(b);
    }
}
