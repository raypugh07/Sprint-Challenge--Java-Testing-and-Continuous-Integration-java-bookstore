package com.lambdaschool.lastjavasprint.service;

import com.lambdaschool.lastjavasprint.model.Book;
import com.lambdaschool.school.view.CountStudentsInCourses;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface BookService
{
    ArrayList<Book> findAll(Pageable pageable);

   // ArrayList<CountStudentsInCourses> getCountStudentsInCourse();

    void delete(long bookid);

    Book save (Book book);

    Book update(Book book, long id);

    void assignAuthor(long bookid,long authid);


}
