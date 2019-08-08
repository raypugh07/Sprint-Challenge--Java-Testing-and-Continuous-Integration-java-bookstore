package com.lambdaschool.lastjavasprint.repository;

import com.lambdaschool.lastjavasprint.model.Instructor;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface InstructorRepository extends CrudRepository<Instructor, Long>
{
    ArrayList<Instructor> findInstructorsByInstructnameEquals (String name);
}
