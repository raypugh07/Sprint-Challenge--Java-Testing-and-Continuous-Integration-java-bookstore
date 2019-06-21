package com.lambdaschool.lastjavasprint.service;


import com.lambdaschool.lastjavasprint.model.Book;
import com.lambdaschool.lastjavasprint.repository.AuthorRepository;
import com.lambdaschool.lastjavasprint.repository.BookRepository;
import com.lambdaschool.school.view.CountStudentsInCourses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "courseService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookrepos;

    @Autowired
    private AuthorRepository authorRepo;

    @Override
    public ArrayList<Book> findAll(Pageable pageable)
    {
        ArrayList<Book> list = new ArrayList<>();
        bookrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

   /* @Override
    public ArrayList<CountStudentsInCourses> getCountStudentsInCourse()
    {
        return courserepos.getCountStudentsInCourse();
    }*/



    @Transactional
    @Override
    public void delete(long bookid) throws EntityNotFoundException
    {
        if (bookrepos.findById(bookid).isPresent())
        {
            bookrepos.deleteBookFromAuthBooks(bookid);
            bookrepos.deleteById(bookid);
        } else
        {
            throw new EntityNotFoundException(Long.toString(bookid));
        }
    }

    @Transactional
    @Override
    public Book save(Book book)
    {
        Book newAuthors = new Book();

        newAuthors.setBooktitle(book.getBooktitle());



        return bookrepos.save(newAuthors);
    }

    @Override
    public Book update(Book book, long id)
    {
        Book currentBook = bookrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (book.getBooktitle() != null)
        {
            currentBook.setBooktitle(book.getBooktitle());
        }

        return bookrepos.save(currentBook);
    }


    @Transactional
    @Override
    public void assignAuthor(long bookid, long authorid) {
        Book currentBook = bookrepos.findById(bookid).orElseThrow(EntityNotFoundException::new);
        currentBook.getAuthors().add(authorRepo.findById(authorid).orElseThrow(EntityNotFoundException::new));
    }

}
