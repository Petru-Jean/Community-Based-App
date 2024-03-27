package org.springprojects.dto.postDTO;

import org.springframework.hateoas.server.core.Relation;
import org.springprojects.validation.PostContentValidation;
import org.springprojects.validation.PostTitleValidation;

@Relation(collectionRelation = "posts", itemRelation = "post")
public class CreateOrViewPostDTO
{
    @PostTitleValidation
    private String title;

    @PostContentValidation
    private String content;

    public CreateOrViewPostDTO(String title, String content)
    {
        this.title   = title;
        this.content = content;
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
}
