package org.springprojects.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.List;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springprojects.validation.CommunityNameValidation;


@Entity
public class Community extends DateAudit
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @CommunityNameValidation
    private String name;

    @Length(max = 512) @NotNull @NotBlank
    private String description;

    @OneToMany(mappedBy = "community")
    @JsonIgnore
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
