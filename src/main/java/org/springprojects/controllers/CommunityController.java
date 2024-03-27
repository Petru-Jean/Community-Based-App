package org.springprojects.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springprojects.dto.communityDTO.CreateOrViewCommunityDTO;
import org.springprojects.services.CommunityService;
import org.springprojects.services.PostService;
import org.springprojects.entities.Community;

@RestController
@Validated
@RequestMapping("/api/v1/communities")
public class CommunityController
{
    private final CommunityService communityService;
    private final PostService postService;

    @Autowired
    public CommunityController(PostService postService, CommunityService communityService)
    {
        this.postService = postService;
        this.communityService = communityService;
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<EntityModel<CreateOrViewCommunityDTO>>> getCommunities()
    {
        return communityService.getCommunities();
    }

    @PostMapping("/")
    public ResponseEntity<EntityModel<CreateOrViewCommunityDTO>> createCommunity(@Valid @RequestBody CreateOrViewCommunityDTO community)
    {
        return communityService.createCommunity(community);
    }

    @GetMapping("/{communityName}")
    public ResponseEntity<EntityModel<Community>> getCommunity(@PathVariable String communityName)
    {
        return communityService.getCommunity(communityName);
    }



}
