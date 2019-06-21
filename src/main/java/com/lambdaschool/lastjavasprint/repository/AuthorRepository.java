package com.lambdaschool.lastjavasprint.repository;

import com.lambdaschool.lastjavasprint.model.Authors;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AuthorRepository extends PagingAndSortingRepository<Authors, Long>
{
    List<Authors> findByFirstnameContainingIgnoreCase(String firstname,String lastname, Pageable pageable);
}
