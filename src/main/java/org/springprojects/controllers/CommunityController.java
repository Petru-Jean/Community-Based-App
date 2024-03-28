package org.springprojects.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    public CommunityController(CommunityService communityService)
    {
        this.communityService = communityService;
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<EntityModel<CreateOrViewCommunityDTO>>> getCommunities()
    {
        return communityService.getCommunities();
    }

    @GetMapping("/{communityName}")
    public ResponseEntity<EntityModel<Community>> getCommunity(@PathVariable String communityName)
    {
        return communityService.getCommunity(communityName);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/")
    public ResponseEntity<EntityModel<CreateOrViewCommunityDTO>> createCommunity(@Valid @RequestBody CreateOrViewCommunityDTO community)
    {
        return communityService.createCommunity(community);
    }



}
