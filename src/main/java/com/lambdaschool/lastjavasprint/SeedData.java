package com.lambdaschool.lastjavasprint;



import com.lambdaschool.lastjavasprint.model.Role;
import com.lambdaschool.lastjavasprint.model.User;
import com.lambdaschool.lastjavasprint.model.UserRoles;
import com.lambdaschool.lastjavasprint.repository.RoleRepository;
import com.lambdaschool.lastjavasprint.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
//turn off seed data when deploying with post and heroku
//@Component
public class SeedData implements CommandLineRunner
{
    RoleRepository rolerepos;
    UserRepository userrepos;


    public SeedData(RoleRepository rolerepos, UserRepository userrepos)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;

    }

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3=new Role("data");

        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));

        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));

        ArrayList<UserRoles> data=new ArrayList<>();
        data.add(new UserRoles(new User(),r3));

        rolerepos.save(r1);
        rolerepos.save(r2);
        rolerepos.save(r3);

        User u1 = new User("barnbarn", "ILuvM4th!", users);
       // u1.getTodos().add(new Todo("Finish java-orders-swagger", "2019-01-13 04:04:04",false, u1));
        /*u1.getTodos().add(new Todo("The enemy of my enemy is the enemy I kill last", u1));
        u1.getTodos().add(new Todo("Beam me up", u1));*/

        User u2 = new User("admin", "password", admins);
       // u2.getTodos().add(new Todo("Feed the turtles", "2019-03-01 04:04:04",false, u2));
        //u2.getTodos().add(new Todo("The question isn't who is going to let me; it's who is going to stop me.", u2));

        User u3=new User("raypugh07","password",data);

        userrepos.save(u1);
        userrepos.save(u2);
        userrepos.save(u3);
    }
}
