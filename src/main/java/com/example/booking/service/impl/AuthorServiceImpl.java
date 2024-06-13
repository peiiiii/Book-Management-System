package com.example.booking.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);


    @Override
    public List<Author> getAllAuthor(){
        // JPQL: Operates on entities, not directly on database tables. It’s portable across different databases because it abstracts the underlying SQL.
        // Native SQL: Directly operates on database tables and columns. It’s database-specific and should be used when you need to perform complex queries that JPQL does not support.
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
        // Query query = entityManager.createNativeQuery("SELECT * FROM author", Author.class);
        // return query.getResultList();
    }

    @Override
    public Optional<Author> getAuthorByName(String name) {
        logger.info("Get author By name: " + name);

        String[] s = name.split(" ");
        List<Author> authors = entityManager.createQuery(
                "SELECT a FROM Author a WHERE a.firstName = :firstName AND a.lastName = :lastName", Author.class)
                .setParameter("firstName", s[0])
                .setParameter("lastName", s[1])
                .getResultList();
        if (authors.isEmpty()) {
            logger.info("Cannot find {}", name);
            return Optional.empty();
        }
        return Optional.of(authors.get(0));
    }

    @Override
    public Optional<Author> getAuthorById(Long id) {
        logger.info("Get author By name: " + id);
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    @Transactional
    public Author updateAuthor(Long id, Author author) {
        logger.info("Update author: " + author);
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
