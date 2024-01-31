package org.springprojects.mapper;


import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.Mapping;
import org.springprojects.dto.PostDTO;
import org.springprojects.entities.Post;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface  PostMapper {

    PostMapper INSTANCE = Mappers.getMapper( PostMapper.class );

    PostDTO carToCarDto(Post post);
    List<PostDTO> map(List<Post> posts);
}
