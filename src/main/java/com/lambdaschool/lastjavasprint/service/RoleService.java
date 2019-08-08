package com.lambdaschool.lastjavasprint.service;




import com.lambdaschool.lastjavasprint.model.Role;

import java.util.List;

public interface RoleService
{
    List<Role> findAll();

    Role findRoleById(long id);

    void delete(long id);

    Role save(Role role);

    Role findByName(String name);
}