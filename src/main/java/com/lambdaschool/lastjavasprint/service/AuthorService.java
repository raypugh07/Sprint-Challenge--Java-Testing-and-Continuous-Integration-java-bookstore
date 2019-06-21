package com.lambdaschool.lastjavasprint.service;

import com.lambdaschool.lastjavasprint.model.Authors;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService
{
    List<Authors> findAll(Pageable pageable);

    Authors findbyAuthId(long id);

    List<Authors> findAuthorByNameLike(String firstname,String lastname, Pageable pageable);

    void delete(long id);

    Authors save (Authors authors);

    Authors update(Authors authors, long id);
}
