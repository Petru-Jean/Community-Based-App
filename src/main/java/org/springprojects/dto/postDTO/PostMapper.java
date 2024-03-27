package org.springprojects.dto.postDTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springprojects.entities.Community;
import org.springprojects.entities.Post;

import java.util.List;

@Mapper
public interface PostMapper
{
    PostMapper INSTANCE = Mappers.getMapper( PostMapper.class );

    List<ViewPostDTO> getPostDTOs(List<Post> posts);


    Post toPost(CreatePostDTO dto);
    Post toPost(ViewPostDTO dto);

}
