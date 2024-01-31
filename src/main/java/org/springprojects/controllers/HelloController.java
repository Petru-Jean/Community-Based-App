package org.springprojects.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springprojects.services.PostService;

@RestController
public class HelloController
{
    private final PostService postService;

    @Autowired
    public HelloController(PostService postService)
    {
        this.postService = postService;
    }

    @GetMapping("/community/{name}/posts/{pageNumber}")
    public ResponseEntity<?> getPostsByCommunityName(@PathVariable String name, @PathVariable int pageNumber)
    {
        return postService.getPostsByCommunityName(name, pageNumber);
    }



}
