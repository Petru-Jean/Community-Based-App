package org.springprojects.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springprojects.dto.communityDTO.CommunityMapper;
import org.springprojects.dto.communityDTO.CreateOrRetrieveCommunityDTO;
import org.springprojects.entities.Post;
import org.springprojects.entities.User;
import org.springprojects.services.CommunityService;
import org.springprojects.services.PostService;
import org.springprojects.entities.Community;

import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<CollectionModel<EntityModel<CreateOrRetrieveCommunityDTO>>> getCommunities()
    {
        return communityService.getCommunities();
    }

    @PostMapping("/")
    public ResponseEntity<EntityModel<CreateOrRetrieveCommunityDTO>> createCommunity(@Valid @RequestBody CreateOrRetrieveCommunityDTO community)
    {
        return communityService.createCommunity(community);
    }

    @GetMapping("/{name}")
    public ResponseEntity<EntityModel<Community>> getCommunity(@PathVariable String name)
    {
        return communityService.getCommunity(name);
    }

    @GetMapping({"/{name}/posts/{pageNumber}", "/{name}/posts"})
    public List<Post> getPosts(@PathVariable String name, @PathVariable(required = false) Integer pageNumber, HttpServletResponse response)
    {
        var responseEntity = communityService.getCommunity(name);

        if(responseEntity == null) return List.of();

        if(pageNumber == null)  pageNumber = 0;

        return postService.getPosts(responseEntity.getBody().getContent().getId(), pageNumber);
    }

    @GetMapping("/{name}/post/{postId}")
    public ResponseEntity<EntityModel<Post>> getPost(@PathVariable int postId)
    {
        var postEntity = postService.findPostById(postId);

        return postEntity;
    }

    @PostMapping("/community/{communityName}/post")
    public ResponseEntity<EntityModel<Post>> createPost(@PathVariable String communityName, @Valid @RequestBody Post post)
    {
        var responseEntity = communityService.getCommunity(communityName);

        User user = new User();
        user.setId(1);

        post.setUser(user);
        post.setCommunity(Objects.requireNonNull(responseEntity.getBody()).getContent());

        return postService.createPost(post);
    }


}
