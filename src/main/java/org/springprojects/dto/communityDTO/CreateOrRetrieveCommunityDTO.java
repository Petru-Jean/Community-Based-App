package org.springprojects.dto.communityDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class CreateOrRetrieveCommunityDTO
{
    @NotNull @NotBlank
    @Length(min = 3, max = 21)
    @Pattern(regexp = "^[A-Za-z0-9_.-]+$")
    private String name;

    @Length(max = 512)
    private String description;

    public CreateOrRetrieveCommunityDTO(String name, String description) {
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
