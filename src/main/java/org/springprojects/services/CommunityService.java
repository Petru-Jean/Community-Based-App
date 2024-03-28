package org.springprojects.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springprojects.controllers.CommunityController;
import org.springprojects.dto.communityDTO.CommunityMapper;
import org.springprojects.dto.communityDTO.CreateCommunityDTO;
import org.springprojects.dto.communityDTO.ViewCommunityDTO;
import org.springprojects.entities.Community;
import org.springprojects.exceptions.AlreadyExistsException;
import org.springprojects.exceptions.NotFoundException;
import org.springprojects.repositories.CommunityRepository;
import org.springframework.hateoas.EntityModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class CommunityService
{
    private final CommunityRepository communityRepository;

    @Autowired
    public CommunityService(CommunityRepository communityRepository)
    {
        this.communityRepository = communityRepository;
    }

    public ResponseEntity<CollectionModel<EntityModel<ViewCommunityDTO>>> getCommunities()
    {
        List<ViewCommunityDTO> communities = CommunityMapper.INSTANCE.toViewDTOList(communityRepository.findAll());

        List<EntityModel<ViewCommunityDTO>> emlist = communities.stream().map(community ->
                EntityModel.of(community,
                        linkTo(CommunityController.class).slash(community.getName()).withSelfRel())).toList();

//        CollectionModel.of(communities);

        CollectionModel<EntityModel<ViewCommunityDTO>> model = CollectionModel.of(emlist);
        model.add(linkTo(CommunityController.class).withSelfRel());

        return new ResponseEntity<>(model, HttpStatus.OK);//, HttpStatus.OK);

        /*return CommunityMapper.INSTANCE.toDTOList(
                EntityModel.of(communityRepository.findAll(),
                              linkTo(CommunityController.class).slash(community.getName()).withSelfRel()) );*/
    }

    public ResponseEntity<EntityModel<ViewCommunityDTO>> getCommunity(String name)
    {
        Community community =  communityRepository.findByName(name);

        if(community == null)
        {
            throw new NotFoundException("Community with name '"+name+"' does not exist");
        }

        ViewCommunityDTO dto = CommunityMapper.INSTANCE.toViewDTO(community);

        EntityModel<ViewCommunityDTO> em  = EntityModel.of(dto, linkTo(CommunityController.class).slash(community.getName()).withSelfRel());

        return new ResponseEntity<EntityModel<ViewCommunityDTO>>(em, HttpStatus.OK);
    }

    public ResponseEntity<EntityModel<CreateCommunityDTO>> createCommunity(CreateCommunityDTO communityDTO)
    {
        if(communityRepository.findByName(communityDTO.getName()) != null)
        {
            throw new AlreadyExistsException("Community with name '" +  communityDTO.getName() + "' already exists");
        }

        Community community = CommunityMapper.INSTANCE.toCommunity(communityDTO);

        communityRepository.save(community);

        EntityModel<CreateCommunityDTO> em  = EntityModel.of(communityDTO, linkTo(CommunityController.class).slash(community.getName()).withSelfRel());

        return new ResponseEntity<EntityModel<CreateCommunityDTO>>(em, HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteCommunity(String name)
    {
        Community community = communityRepository.findByName(name);

        if(community == null)
        {
            throw new NotFoundException("Community with name '" + name + "' does not exist");
        }

        communityRepository.delete(community);

        return new ResponseEntity<>("Community with name '" + name + "' has been deleted", HttpStatus.NO_CONTENT);
    }


    public List<Community> findAll()
    {
        return communityRepository.findAll();
    }

}
