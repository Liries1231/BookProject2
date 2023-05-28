package com.example.BookProject.repos;

import com.example.BookProject.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepos extends CrudRepository<Author, Integer> {
}

