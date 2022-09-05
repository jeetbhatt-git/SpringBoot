package com.api.book.bootapibook.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "author_id")
    private int authorId;
    
    @Column(name= "first_name")
    private String firstName;
 
    @Column(name= "last_name")
    private String lastName;

    private String language;
    
    //JsonBackReference binds the program so that it doesn't go back to Book class as it causes 
    //infinite loop from author to book and vice-versa
    // Along with it JsonManagedReference is also used
    @OneToOne( mappedBy = "author")
    @JsonBackReference
    private Book book;

   

    public Author() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    public int getAuthorId() {
        return authorId;
    }
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    
    
    
}
