package com.api.book.bootapibook.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.bootapibook.dao.BookRepository;
import com.api.book.bootapibook.entities.Book;

@Component
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    // private static List<Book> list = new ArrayList();

    // static{
    //     list.add(new Book(11,"Lord of Ring", "Max"));
    //     list.add(new Book(12,"Last Man", "Michale"));
    //     list.add(new Book(13,"Lone Range", "Mickey"));
    // }
    

    public List<Book> getAllBooks(){
        List<Book> list = (List<Book>) this.bookRepository.findAll();
        return list;
    }

    public Book getBookById(int id){
        Book book = null;
        try{
        //book =  list.stream().filter(e ->e.getId()==id).findFirst().get();
        book = this.bookRepository.findById(id);
    }catch(Exception e){
        e.printStackTrace();
    }
        return book;
    }

    //Add new book POST
    public Book addBook(Book b){
        Book result = bookRepository.save(b);
      //  list.add(b);
        return result;
    }

    public void deleteBook(int bid){
        bookRepository.deleteById(bid);
    }

    public void updateBook(Book book, int id){
         
         book.setId(id);
         bookRepository.save(book);
    }
}
