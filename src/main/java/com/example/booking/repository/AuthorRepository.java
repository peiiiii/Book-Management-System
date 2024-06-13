package com.example.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.booking.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>  {
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
