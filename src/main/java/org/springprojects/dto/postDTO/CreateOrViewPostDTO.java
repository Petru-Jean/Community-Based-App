package org.springprojects.dto.postDTO;

import org.springprojects.validation.PostContentValidation;
import org.springprojects.validation.PostTitleValidation;

public class CreateOrViewPostDTO
{
    @PostTitleValidation
    private String title;

    @PostContentValidation
    private String content;
}
