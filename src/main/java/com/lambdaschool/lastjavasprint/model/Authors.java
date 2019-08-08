package com.lambdaschool.lastjavasprint.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "author",description = "The author entity")
@Entity
@Table(name = "author")
public class Authors
{
    @ApiModelProperty(name="authid",value = "Primary key for authors",required = true,example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authid;

    private String firstname;
    private String lastname;

    @ManyToMany
    @JoinTable(name = "authbooks",
               joinColumns = {@JoinColumn(name = "authid")},
               inverseJoinColumns = {@JoinColumn(name = "bookid")})
    @JsonIgnoreProperties("author")
    private List<Book> books = new ArrayList<>();

    public Authors()
    {
    }

    public Authors(String firstname,String lastname)
    {

        this.firstname = firstname;
        this.lastname=lastname;
    }

    public long getAuthid()
    {
        return authid;
    }

    public void setAuthid(long authid)
    {
        this.authid = authid;
    }

   /* public String getStudname()
    {
        return studname;
    }

    public void setStudname(String studname)
    {
        this.studname = studname;
    }*/

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

  /*  public List<Book> getCours()
    {
        return cours;
    }

    public void setCours(List<Book> cours)
    {
        this.cours = cours;
    }*/
}
