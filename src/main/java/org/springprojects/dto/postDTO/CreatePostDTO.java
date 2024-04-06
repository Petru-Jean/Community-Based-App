package org.springprojects.dto.postDTO;

import jakarta.persistence.EntityListeners;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springprojects.validation.PostContentValidation;
import org.springprojects.validation.PostTitleValidation;

import java.sql.Timestamp;
import java.util.Date;

public class CreatePostDTO
{
    @PostTitleValidation
    private String title;

    @PostContentValidation
    private String content;

    private Timestamp createdAt;

    public CreatePostDTO(String title, String content, Timestamp createdAt)
    {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }


    public Timestamp getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt)
    {
        this.createdAt = createdAt;
    }
}
