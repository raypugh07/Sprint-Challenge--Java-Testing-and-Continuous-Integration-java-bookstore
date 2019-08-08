package com.lambdaschool.lastjavasprint.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@ApiModel(value = "instructor",description = "The instructor entity")
@Entity
@Table(name = "instructor")
public class Instructor
{
    @ApiModelProperty(name="isntructid",value = "Primary key for instructors",required = true,example = "1")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long instructid;

    private String instructname;

   /* @OneToMany(mappedBy = "instructor")
    @JsonIgnoreProperties("instructors")
    private List<Book> cours = new ArrayList<>();*/

    public Instructor()
    {
    }

    public Instructor(String instructname)
    {
        this.instructname = instructname;
    }

    public long getInstructid()
    {
        return instructid;
    }

    public void setInstructid(long instructid)
    {
        this.instructid = instructid;
    }

    public String getInstructname()
    {
        return instructname;
    }

    public void setInstructname(String instructname)
    {
        this.instructname = instructname;
    }

    /*public List<Book> getCours()
    {
        return cours;
    }

    public void setCours(List<Book> cours)
    {
        this.cours = cours;
    }*/
}
