package org.springprojects.dto.communityDTO;

import org.springframework.hateoas.server.core.Relation;

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
