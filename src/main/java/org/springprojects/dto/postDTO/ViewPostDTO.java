package org.springprojects.dto.postDTO;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.Relation;
import org.springprojects.controllers.UserController;
import org.springprojects.dto.userDTO.ViewUserDTO;
import org.springprojects.entities.User;
import org.springprojects.validation.PostContentValidation;
import org.springprojects.validation.PostTitleValidation;

import java.sql.Timestamp;
import java.util.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Relation(collectionRelation = "posts", itemRelation = "post")
public class ViewPostDTO
{
    //UUID.randomUUID().toString().replace("-","").substring(0,8)

    @PostTitleValidation
    private String title;

    @PostContentValidation
    private String content;

    private EntityModel<ViewUserDTO> user;
    private String externalId;
    private Timestamp createdAt;

    public ViewPostDTO(String title, String content, String externalId, Timestamp createdAt, ViewUserDTO user)
    {
        this.title       = title;
        this.content     = content;
        this.externalId  = externalId;
        this.createdAt   = createdAt;
        this.user = EntityModel.of(user, linkTo(UserController.class).slash(user.getUsername()).withSelfRel());
    }

    public void setCreatedAt(Timestamp createdAt)
    {
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

    public String getExternalId()
    {
        return externalId;
    }

    public void setExternalId(String externalId)
    {
        this.externalId = externalId;
    }

    public EntityModel<ViewUserDTO> getUser()
    {
        return user;
    }

    public void setUser(ViewUserDTO user)
    {
        this.user = EntityModel.of(user, linkTo(UserController.class).slash(user.getUsername()).withSelfRel());
    }

    private Date getCreatedAt()
    {
        return createdAt;
    }

    public void setUser(EntityModel<ViewUserDTO> user)
    {
        this.user = user;
    }
}
