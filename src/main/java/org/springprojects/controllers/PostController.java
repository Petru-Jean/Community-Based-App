package org.springprojects.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springprojects.dto.postDTO.CreatePostDTO;
import org.springprojects.dto.postDTO.ViewPostDTO;
import org.springprojects.entities.Post;
import org.springprojects.services.PostService;

@RestController
@Validated
@RequestMapping(value = "/api/v1/communities/{communityName}")
public class PostController
{
    private final PostService      postService;

    @Autowired
    public PostController(PostService postService)
    {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<CollectionModel<EntityModel<ViewPostDTO>>> getPosts(@PathVariable String communityName, @RequestParam(required = false) Integer pageNumber)
    {
        return postService.getPosts(communityName, pageNumber == null || pageNumber < 0 ? 0 : pageNumber);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<EntityModel<Post>> getPost(@PathVariable int postId)
    {
        var postEntity = postService.findPostById(postId);

        return postEntity;
    }

    @PostMapping("/posts")
    public ResponseEntity<EntityModel<CreatePostDTO>> createPost(@PathVariable String communityName, @Valid @RequestBody CreatePostDTO postDTO)
    {
        return postService.createPost(communityName, postDTO);
    }

}
