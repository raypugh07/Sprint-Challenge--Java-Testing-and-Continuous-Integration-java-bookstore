package com.lambdaschool.lastjavasprint.controller;

import com.lambdaschool.lastjavasprint.model.Authors;
import com.lambdaschool.lastjavasprint.model.ErrorDetail;
import com.lambdaschool.lastjavasprint.service.AuthorService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController
{
    @Autowired
    private AuthorService authorService;

    // Please note there is no way to add students to course yet!

    @ApiOperation(value = "return all authors", response = Authors.class,responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> listAllAuthors(@PageableDefault(page = 0,size = 2) Pageable pageable)
    {
        List<Authors> myAuthors = authorService.findAll(pageable);
        return new ResponseEntity<>(myAuthors, HttpStatus.OK);
    }

    @ApiOperation(value = "return author by id", response = Authors.class)
    @ApiResponses(value={
            @ApiResponse(code=201,message="Authors Found",response=Authors.class),
            @ApiResponse(code=404,message="Authors Not Found",response= ErrorDetail.class)
    })
    @GetMapping(value = "/author/{authid}",
                produces = {"application/json"})
    public ResponseEntity<?> getAuthorById(@ApiParam(value = "Authors id",required = true,example = "1")
            @PathVariable
                    Long authid)
    {
        Authors a = authorService.findbyAuthId(authid);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }


    @GetMapping(value = "/author/namelike/{firstname}",
                produces = {"application/json"})
    public ResponseEntity<?> getAuthorsByNameContaining(
            @PathVariable String firstname,String lastname,@PageableDefault(page = 0,size = 2)Pageable pageable)
    {
        List<Authors> myAuthors = authorService.findAuthorByNameLike(firstname,lastname,pageable);
        return new ResponseEntity<>(myAuthors, HttpStatus.OK);
    }

    @ApiOperation(value = "add author", notes = "The newly created author id will be sent in the location header",
    response = void.class)
    @ApiResponses(value={
            @ApiResponse(code=201,message="Authors created",response=void.class),
            @ApiResponse(code=500,message="Error creating author",response= ErrorDetail.class)
    })
    @PostMapping(value = "/author",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewAuthor(@Valid
                                           @RequestBody
                                                   Authors newAuthor) throws URISyntaxException
    {
        newAuthor = authorService.save(newAuthor);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newStudentURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{authid}").buildAndExpand(newAuthor.getAuthid()).toUri();
        responseHeaders.setLocation(newStudentURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @PutMapping(value = "/author/{authid}")
    public ResponseEntity<?> updateAuthor(
            @RequestBody
                    Authors updateAuthor,
            @PathVariable
                    long authid)
    {
        authorService.update(updateAuthor, authid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/author/{authid}")
    public ResponseEntity<?> deleteAuthorById(
            @PathVariable
                    long authid)
    {
        authorService.delete(authid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
