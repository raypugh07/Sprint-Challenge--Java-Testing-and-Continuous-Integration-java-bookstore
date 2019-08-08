package com.lambdaschool.lastjavasprint.service;

import com.lambdaschool.lastjavasprint.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "instructorService")
public class InstructorServiceImpl implements InstructorService
{
    @Autowired
    private InstructorRepository instructrepos;
}
