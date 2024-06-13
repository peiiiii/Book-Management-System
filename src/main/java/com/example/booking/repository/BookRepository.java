package com.example.booking.repository;

import org.springframework.stereotype.Repository;

import com.example.booking.domain.Book;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>  {
    Optional<Book> findByTitle(String title);
    void deleteByTitle(String title);
}
