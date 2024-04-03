package org.springprojects.dto.communityDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.server.core.Relation;
import org.springprojects.validation.CommunityNameValidation;

@Relation(collectionRelation = "communities", itemRelation = "community")
public class CreateCommunityDTO
{
    @CommunityNameValidation
    private String name;

    @Length(max = 512) @NotNull @NotBlank
    private String description;

    public CreateCommunityDTO(String name, String description) {
        this.name = name;
        this.description = description;
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
