package org.springprojects.dto.userDTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.EntityModel;
import org.springprojects.entities.User;

@Mapper
public interface UserMapper
{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    ViewUserDTO map(User user);
    User map(ViewUserDTO dto);

}

