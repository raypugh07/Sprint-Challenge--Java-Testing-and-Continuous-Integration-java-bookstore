package com.lambdaschool.lastjavasprint.service;


import com.lambdaschool.lastjavasprint.model.Authors;
import com.lambdaschool.lastjavasprint.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "studentService")
public class AuthorServiceImpl implements AuthorService
{
    @Autowired
    private AuthorRepository authrepos;

    @Override
    public List<Authors> findAll(Pageable pageable)
    {
        List<Authors> list = new ArrayList<>();
        authrepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Authors findbyAuthId(long id) throws EntityNotFoundException
    {
        return authrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public List<Authors> findAuthorByNameLike(String firstname,String lastname, Pageable pageable)
    {
        List<Authors> list = new ArrayList<>();
        authrepos.findByFirstnameContainingIgnoreCase(firstname,lastname,pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (authrepos.findById(id).isPresent())
        {
            authrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Authors save(Authors authors)
    {
        Authors newAuthors = new Authors();

        newAuthors.setFirstname(authors.getFirstname());
        newAuthors.setLastname(authors.getLastname());


        return authrepos.save(newAuthors);
    }

    @Override
    public Authors update(Authors authors, long id)
    {
        Authors currentAuthors = authrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (authors.getFirstname() != null)
        {
            currentAuthors.setFirstname(authors.getFirstname());
        }
        if (authors.getLastname() != null)
        {
            currentAuthors.setLastname(authors.getLastname());
        }

        return authrepos.save(currentAuthors);
    }
}
