package com.api.book.bootapibook.controllers;

import java.util.List;
import java.util.Optional;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootapibook.entities.Book;
import com.api.book.bootapibook.services.BookService;

// @Controller
//For Rest API we use RestController in place @Controller and @ResponseBody is not needed

@RestController
public class BookController {

    // @RequestMapping(value = "/books", method = RequestMethod.GET)   > THis is replaced by line @GetMapping
    // @ResponseBody

    @RequestMapping(value="/test", method = RequestMethod.GET)
    @ResponseBody
    private String testController(){
        return "Hello Controller test";
    }

    

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
          System.out.println("Get GET  conroller test");
        List<Book> list = null;
        list = this.bookService.getAllBooks();

        if(list.size()==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // Jackson in the backend convert book obj to JSON
        return ResponseEntity.of(Optional.of(list));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id){
        Book book = bookService.getBookById(id);

        if(book== null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }  
    
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book b){
        Book book = null;
        try{
         book = this.bookService.addBook(b);
         System.out.println("post conroller test");
        return ResponseEntity.status(HttpStatus.CREATED).build();  
        }catch(Exception e){
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int bid){
        try{
            this.bookService.deleteBook(bid);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception e){
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book){

                    try{
                        this.bookService.updateBook(book, id);
                        return ResponseEntity.of(Optional.of(book)); 

                    }catch(Exception e){
                        e.printStackTrace();
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }

    }
}
