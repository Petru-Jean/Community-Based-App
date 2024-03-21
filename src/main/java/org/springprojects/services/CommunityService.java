package org.springprojects.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springprojects.entities.Community;
import org.springprojects.exceptions.AlreadyExistsException;
import org.springprojects.repositories.CommunityRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

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

    public Community findByName(String name)
    {
        return communityRepository.findByName(name);
    }

    public ResponseEntity<EntityModel<Community>> create(String name, String description)
    {
        if(communityRepository.findByName(name) != null)
        {
            throw new AlreadyExistsException("Community '" +  name + "' already exists");
        }

        Community community = new Community();

        community.setName(name);
        community.setDescription(description);

        communityRepository.save(community);


        EntityModel<Community> em  = EntityModel.of(community, linkTo(Community.class).slash(community.getName()).withSelfRel());

        return new ResponseEntity<EntityModel<Community>>(em, HttpStatus.CREATED);
    }

}
