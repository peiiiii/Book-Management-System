package com.example.booking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.booking.domain.Author;
import com.example.booking.service.AuthorService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class AuthorServiceImpl implements AuthorService{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Author> getAllAuthor(){
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

    @Override
    public Optional<Author> getAuthorByName(String name) {

        String[] s = name.split(" ");
        List<Author> authors = entityManager.createQuery(
                "SELECT a FROM Author a WHERE a.firstName = :firstName AND a.lastName = :lastName", Author.class)
                .setParameter("firstName", s[0])
                .setParameter("lastName", s[1])
                .getResultList();
        if (authors.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(authors.get(0));
    }

    @Override
    public Optional<Author> getAuthorById(Long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    @Transactional
    public Author updateAuthor(Long id, Author author) {
        return entityManager.merge(author);
    }

    @Override
    @Transactional
    public void deleteAuthorById(Long id) {
        Author author = entityManager.find(Author.class, id);
        if (author != null) {
            entityManager.remove(author);
        }
    }

}
