package com.lambdaschool.lastjavasprint.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@ApiModel(value = "book",description = "The book entity")

@Entity
@Table(name = "book")
public class Book
{
    @ApiModelProperty(name="bookid",value = "Primary key for book",required = true,example = "1")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @JsonView(View.CoursesOnly.class)
    private long bookid;

//    @JsonView(View.CoursesOnly.class)
    private String booktitle;
    private String isbn;
    private int copy;

   /* @ManyToOne
    @JoinColumn(name = "instructid")
    @JsonIgnoreProperties("courses")
//    @JsonView(View.CoursesOnly.class)
    private Instructor instructor;*/

    @ManyToMany(mappedBy = "books")
    @JsonIgnoreProperties("books")
    private List<Authors> authors = new ArrayList<>();

    public Book()
    {
    }

    public Book(String booktitle)
    {
        this.booktitle = booktitle;
    }

    public Book(String isbn, int copy) {
        this.isbn = isbn;
        this.copy = copy;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }
    /*public Book(String booktitle, Instructor instructor)
    {
        this.coursename = coursename;
        this.instructor = instructor;
    }*/

    public long getBookid()
    {
        return bookid;
    }

    public void setBookid(long bookid)
    {
        this.bookid = bookid;
    }

    public String getBooktitle()
    {
        return booktitle;
    }

    public void setBooktitle(String booktitle)
    {
        this.booktitle = booktitle;
    }

    /*public Instructor getInstructor()
    {
        return instructor;
    }

    public void setInstructor(Instructor instructor)
    {
        this.instructor = instructor;
    }*/



    public List<Authors> getAuthors()
    {
        return authors;
    }

    public void setAuthors(List<Authors> authors)
    {
        this.authors = authors;
    }
}
