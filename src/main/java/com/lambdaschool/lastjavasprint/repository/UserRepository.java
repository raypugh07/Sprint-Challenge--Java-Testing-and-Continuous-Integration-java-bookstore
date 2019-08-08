package com.lambdaschool.lastjavasprint.repository;

//import com.lambdaschool.authenticatedusers.model.User;
import com.lambdaschool.lastjavasprint.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
