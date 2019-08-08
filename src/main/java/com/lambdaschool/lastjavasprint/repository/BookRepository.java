package com.lambdaschool.lastjavasprint.repository;

import com.lambdaschool.lastjavasprint.model.Book;
import com.lambdaschool.school.view.CountStudentsInCourses;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface BookRepository extends CrudRepository<Book, Long>
{
    ArrayList<Book> findBooksByBooktitleEquals(String booktitle);


    @Modifying
    @Query(value = "DELETE FROM authbooks WHERE bookid = :bookid", nativeQuery = true)
    void deleteBookFromAuthBooks(long bookid);

   /* @Query(value = "SELECT s.courseid, coursename, count(studid) as countstudents FROM studcourses s INNER JOIN course c on s.courseid=c.courseid GROUP BY s.courseid, coursename", nativeQuery = true)
    ArrayList<CountStudentsInCourses> getCountStudentsInCourse();*/
}

