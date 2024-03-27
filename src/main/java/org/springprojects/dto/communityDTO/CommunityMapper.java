package org.springprojects.dto.communityDTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springprojects.entities.Community;

import java.util.List;

@Mapper
public interface CommunityMapper
{
    CommunityMapper INSTANCE = Mappers.getMapper( CommunityMapper.class );

    Community toCommunity(CreateOrViewCommunityDTO community);

    CreateOrViewCommunityDTO toDTO(Community community);

    List<CreateOrViewCommunityDTO> toDTOList(List<Community> communities);

}
