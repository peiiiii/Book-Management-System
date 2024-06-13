package com.example.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.domain.Book;
import com.example.booking.service.BookService;


@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<?> getAllBook(){
        return new ResponseEntity<>(bookService.getAllBook(), HttpStatus.OK);
    }

    @GetMapping(params = "title")
    public ResponseEntity<?> getBookByTitle(@RequestParam String title){
        return new ResponseEntity<>(bookService.getBookByTitle(title), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable long id, @RequestBody Book book){
        return new ResponseEntity<>(bookService.updateBook(id, book), HttpStatus.OK);
    }

    @DeleteMapping(params = "title")
    public ResponseEntity<Void> deleteBookByTitle(@RequestParam String title){
        try {
            bookService.deleteBookByTitle(title);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> deleteBookById(@RequestParam Long id){
        try {
            bookService.deleteBookById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
