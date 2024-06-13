package com.example.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.domain.Author;
import com.example.booking.service.AuthorService;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<?> getAllAuthor(){
        return new ResponseEntity<>(authorService.getAllAuthor(), HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<?> getAuthorByName(@RequestParam String name){
        return new ResponseEntity<>(authorService.getAuthorByName(name), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable long id, @RequestBody Author author){
        return new ResponseEntity<>(authorService.updateAuthor(id, author), HttpStatus.OK);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<?> deleteAuthorById(@RequestParam Long id){
        try {
            authorService.deleteAuthorById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
