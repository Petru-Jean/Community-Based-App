package org.springprojects.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springprojects.controllers.CommunityController;
import org.springprojects.dto.communityDTO.CommunityMapper;
import org.springprojects.dto.communityDTO.CreateOrRetrieveCommunityDTO;
import org.springprojects.entities.Community;
import org.springprojects.entities.Post;
import org.springprojects.exceptions.AlreadyExistsException;
import org.springprojects.exceptions.HandledException;
import org.springprojects.exceptions.NotFoundException;
import org.springprojects.repositories.CommunityRepository;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<EntityModel<Community>> findByName(String name)
    {
        Community community =  communityRepository.findByName(name);

        if(community == null)
        {
            throw new NotFoundException("Community with name '"+name+"' does not exist.");
        }

        EntityModel<Community> em  = EntityModel.of(community, linkTo(CommunityController.class).slash(community.getName()).withSelfRel());

        return new ResponseEntity<EntityModel<Community>>(em, HttpStatus.OK);
    }

    public ResponseEntity<EntityModel<CreateOrRetrieveCommunityDTO>> create(CreateOrRetrieveCommunityDTO communityDTO)
    {
        if(communityRepository.findByName(communityDTO.getName()) != null)
        {
            throw new AlreadyExistsException("Community with name '" +  communityDTO.getName() + "' already exists.");
        }

        Community community = CommunityMapper.INSTANCE.toCommunity(communityDTO);

        communityRepository.save(community);

        EntityModel<CreateOrRetrieveCommunityDTO> em  = EntityModel.of(communityDTO, linkTo(CommunityController.class).slash(community.getName()).withSelfRel());

        return new ResponseEntity<EntityModel<CreateOrRetrieveCommunityDTO>>(em, HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteCommunity(String name)
    {
        Community community = communityRepository.findByName(name);

        if(community == null)
        {
            throw new NotFoundException("Community with name '" + name + "' does not exist.");
        }

        communityRepository.delete(community);

        return new ResponseEntity<>("Community with name '" + name + "' has been deleted.", HttpStatus.NO_CONTENT);
    }


    public List<Community> findAll()
    {
        return communityRepository.findAll().stream().toList();
    }

}
