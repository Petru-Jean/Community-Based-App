package org.springprojects.controllers;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springprojects.entities.Post;
import org.springprojects.services.CommunityService;
import org.springprojects.services.PostService;
import org.springprojects.entities.Community;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/api/v1/community")
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

    @PostMapping("/create")
    public String createCommunity(@Valid @RequestBody Community community)
    {
        return "Success: " + community.getName();
    }

    @GetMapping({"/{communityName}/posts/{pageNumber}", "{communityName}/posts"})
    public List<Post> getPosts(@PathVariable String communityName, @PathVariable(required = false) Integer pageNumber, HttpServletResponse response)
    {
        Community community = communityService.findByName(communityName);

        if(community == null)
        {
            response.setStatus(ExpiresFilter.XHttpServletResponse.SC_NOT_FOUND);
            return List.of();
        }

        if(pageNumber == null)
            pageNumber = 0;

        return postService.getPosts(community.getId(), pageNumber);
    }



}
