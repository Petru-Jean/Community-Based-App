package org.springprojects.dto.communityDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.server.core.Relation;
import org.springprojects.entities.DateAudit;
import org.springprojects.validation.CommunityNameValidation;

import java.util.Date;

@Relation(collectionRelation = "communities", itemRelation = "community")
public class ViewCommunityDTO
{
    private String name;

    private String description;

    private Date createdAt;

    public ViewCommunityDTO(String name, String description, Date createdAt) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
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

    public void setCreatedAt(Date date)
    {
        this.createdAt = date;
    }

}
