package com.api.book.bootapibook.dao;

import org.springframework.data.repository.CrudRepository;

import com.api.book.bootapibook.entities.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
    public Book findById(int id);
    
}
