package org.springprojects.controllers;


import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springprojects.dto.PostDTO;
import org.springprojects.services.CommunityService;
import org.springprojects.services.PostService;
import org.springprojects.entities.Community;

import java.util.List;

@RestController
@RequestMapping("/community/{communityName}")
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

    @GetMapping("posts/{pageNumber}")
    public List<PostDTO> getPosts(@PathVariable String communityName, @PathVariable int pageNumber, HttpServletResponse response)
    {
        Community community = communityService.findByName(communityName);

        if(community == null)
        {
            response.setStatus(ExpiresFilter.XHttpServletResponse.SC_NOT_FOUND);
            return List.of();
        }

        return postService.getPosts(community.getId(), pageNumber);
    }



}
