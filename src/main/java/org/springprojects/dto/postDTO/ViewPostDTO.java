package org.springprojects.dto.postDTO;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.Relation;
import org.springprojects.controllers.CommunityController;
import org.springprojects.controllers.PostController;
import org.springprojects.controllers.UserController;
import org.springprojects.dto.userDTO.ViewUserDTO;
import org.springprojects.validation.PostContentValidation;
import org.springprojects.validation.PostTitleValidation;
import org.springprojects.entities.User;

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

    public ViewPostDTO(String title, String content, String externalId)
    {
        this.title = title;
        this.content = content;
        this.externalId = externalId;
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

}
