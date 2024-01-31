package org.springprojects.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springprojects.entities.Community;
import org.springprojects.entities.Post;
import org.springprojects.repositories.CommunityRepository;
import org.springprojects.repositories.PostRepository;

import java.util.List;

@Service
public class PostService
{
    private final CommunityRepository communityRepository;
    private final PostRepository      postRepository;

    @Autowired
    public PostService(CommunityRepository communityRepository, PostRepository postRepository)
    {
        this.communityRepository = communityRepository;
        this.postRepository = postRepository;
    }

    public ResponseEntity<?> getPostsByCommunityName(String name, int pageNumber)
    {
        Community community = communityRepository.findByName(name);

        if(community == null)
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }

        if(pageNumber < 0) pageNumber = 0;

        Pageable pageable = PageRequest.of(pageNumber, 25);

        List<Post> posts = postRepository.findAllByCommunityId(
                community.getId(),
                pageable);

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

}
