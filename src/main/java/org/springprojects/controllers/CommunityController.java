package org.springprojects.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springprojects.entities.Post;
import org.springprojects.entities.User;
import org.springprojects.security.UsernameAndPasswordAuthentication;
import org.springprojects.services.CommunityService;
import org.springprojects.services.PostService;
import org.springprojects.entities.Community;

import java.util.List;
import java.util.Objects;

@RestController
@Validated
@RequestMapping("/api/v1")
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

    @PostMapping("/community")
    public ResponseEntity<?> createCommunity(@Valid @RequestBody Community community)
    {
        return communityService.create(community.getName(), community.getDescription());
    }

    @GetMapping("/community/{name}")
    public ResponseEntity<EntityModel<Community>> getCommunity(@PathVariable String name)
    {
        return communityService.findByName(name);
    }

    @GetMapping({"/community/{communityName}/posts/{pageNumber}", "/community/{communityName}/posts"})
    public List<Post> getPosts(@PathVariable String communityName, @PathVariable(required = false) Integer pageNumber, HttpServletResponse response)
    {
        var responseEntity = communityService.findByName(communityName);

        if(pageNumber == null)  pageNumber = 0;

        return postService.getPosts(responseEntity.getBody().getContent().getId(), pageNumber);
    }

    @GetMapping("/community/{communityName}/post/{postId}")
    public ResponseEntity<EntityModel<Post>> getPost(@PathVariable int postId)
    {
        var postEntity = postService.findPostById(postId);

        return postEntity;
    }

    @PostMapping("/community/{communityName}/post")
    public ResponseEntity<EntityModel<Post>> createPost(@PathVariable String communityName, @Valid @RequestBody Post post)
    {
        var responseEntity = communityService.findByName(communityName);

        User user = new User();
        user.setId(1);

        post.setUser(user);
        post.setCommunity(Objects.requireNonNull(responseEntity.getBody()).getContent());

        return postService.createPost(post);
    }

    @GetMapping("/communities")
    public List<Community> getCommunities()
    {
        return communityService.findAll();
    }

    @PatchMapping("/community/{communityName}/post/{postId}")
    public ResponseEntity<EntityModel<Post>> updatePost(@PathVariable int postId, @RequestBody String content)
    {
        return postService.updatePost(postId, content);
    }

    @DeleteMapping("/community/{communityName}/post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable int postId)
    {
        return postService.deletePost(postId);
    }

    @DeleteMapping("/communities/delete/{communityName")
    public ResponseEntity<String> deleteCommunity(@PathVariable String communityName)
    {
        return communityService.deleteCommunity(communityName);
    }

}
