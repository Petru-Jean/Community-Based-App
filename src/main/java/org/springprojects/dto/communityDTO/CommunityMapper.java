package org.springprojects.dto.communityDTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springprojects.entities.Community;

import javax.swing.text.View;
import java.util.List;

@Mapper
public interface CommunityMapper
{
    CommunityMapper INSTANCE = Mappers.getMapper( CommunityMapper.class );

    Community toCommunity(ViewCommunityDTO communityDTO);
    Community toCommunity(CreateCommunityDTO communityDTO);

    ViewCommunityDTO toViewDTO(Community community);
    CreateCommunityDTO toCreateDTO(Community community);

    List<CreateCommunityDTO> toCreateDTOList(List<Community> communities);
    List<ViewCommunityDTO> toViewDTOList(List<Community> communities);
}
