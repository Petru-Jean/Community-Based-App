package org.springprojects.dto.postDTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.EntityModel;
import org.springprojects.dto.userDTO.ViewUserDTO;
import org.springprojects.entities.Community;
import org.springprojects.entities.Post;
import org.springprojects.entities.User;

import java.util.List;

@Mapper
public interface PostMapper
{
    PostMapper INSTANCE = Mappers.getMapper( PostMapper.class );

    List<ViewPostDTO> getPostDTOs(List<Post> posts);

    ViewPostDTO toViewPostDTO(Post post);
    CreatePostDTO toCreatePostDTO(Post post);

    Post toPost(CreatePostDTO dto);
    Post toPost(ViewPostDTO dto);


}
