package org.springprojects.controllers;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springprojects.dto.PostDTO;
import org.springprojects.services.PostService;

import java.util.List;

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
    public List<PostDTO> getPostsByCommunityName(@PathVariable String name, @PathVariable int pageNumber, HttpServletResponse response)
    {
        return postService.getPostsByCommunityName(name, pageNumber, response);
    }



}
