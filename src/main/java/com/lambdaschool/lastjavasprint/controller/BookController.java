package com.lambdaschool.lastjavasprint.controller;


import com.lambdaschool.lastjavasprint.model.Authors;
import com.lambdaschool.lastjavasprint.model.Book;
import com.lambdaschool.lastjavasprint.model.ErrorDetail;
import com.lambdaschool.lastjavasprint.service.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController
{
    @Autowired
    private BookService bookService;
    @ApiOperation(value = "Return all Books", response = Book.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})

    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(Pageable pageable){
        return new ResponseEntity<>(bookService.findAll(pageable), HttpStatus.OK);
    }


    @ApiOperation(value = "Update book", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "book update", response = void.class),
            @ApiResponse(code = 500, message = "book not update", response = ErrorDetail.class)
    })
    @PutMapping(value = "/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable long id, @RequestBody Book book){
        bookService.update(book, id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @ApiOperation(value = "match book with author", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "book matched", response = void.class),
            @ApiResponse(code = 500, message = "book did not match", response = ErrorDetail.class)
    })
    @PostMapping(value = "/data/books/{id}")
    public ResponseEntity<?> matchBookWithAuthor(@PathVariable long id, @RequestBody Authors authors){
        bookService.assignAuthor(id, authors.getAuthid());
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @DeleteMapping("/books/{bookid}")
    public ResponseEntity<?> deleteCourseById(@PathVariable long bookid)
    {
        bookService.delete(bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
