package org.springprojects.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springprojects.validation.PostContentValidation;
import org.springprojects.validation.PostExternalIdValidation;
import org.springprojects.validation.PostTitleValidation;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(schema = "api")
public class Post extends Votable
{
    @PostTitleValidation
    private String title;

    @PostContentValidation
    private String content;

    @PostExternalIdValidation
    @Column(name = "external_id")
    private String externalId;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "community_id")
    @JsonIgnore
    private Community community;

    public String getTitle() {
        return  title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public String getExternalId()
    {
        return externalId;
    }

    public void setExternalId(String externalId)
    {
        this.externalId = externalId;
    }

    @Override
    public String toString()
    {
        return "Post{" +
                "id=" + super.getId() +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", community=" + community +
                ", user=" + super.getUser() +
                '}';
    }
}
