package org.springprojects.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springprojects.validation.CommunityNameValidation;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(schema = "api")
public class Community implements Serializable
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CommunityNameValidation
    private String name;

    @Length(max = 512) @NotNull @NotBlank
    private String description;

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY)
    private List<Post> posts;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

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

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }
}
