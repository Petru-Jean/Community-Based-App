package org.springprojects.services;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.ExpiresFilter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springprojects.controllers.CommunityController;
import org.springprojects.dto.postDTO.CreateOrViewPostDTO;
import org.springprojects.dto.postDTO.PostMapper;
import org.springprojects.entities.Community;
import org.springprojects.entities.Post;
import org.springprojects.entities.User;
import org.springprojects.exceptions.NotFoundException;
import org.springprojects.repositories.CommunityRepository;
import org.springprojects.repositories.PostRepository;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class PostService
{
    private final PostRepository     postRepository;
    private final CommunityService   communityService;

    @Autowired
    public PostService(PostRepository postRepository, CommunityService communityService)
    {
        this.postRepository   = postRepository;
        this.communityService = communityService;
    }

    public List<Post> getPosts(int communityId, int pageNumber)
    {
        pageNumber = Math.max(pageNumber, 0);

        Pageable pageable = PageRequest.of(pageNumber, 25);

        return postRepository.findAllByCommunityId(communityId, pageable);
    }   

    public ResponseEntity<EntityModel<CreateOrViewPostDTO>> createPost(String communityName, CreateOrViewPostDTO postDTO)
    {
        var communityResponseEntity = communityService.getCommunity(communityName);

        if(communityResponseEntity == null || (communityResponseEntity.getBody()) == null  || communityResponseEntity.getBody().getContent() == null)
        {
            throw new NotFoundException("Post not created because the community '" + communityName + "' does not exist");
        }

        Community community = communityResponseEntity.getBody().getContent();

        Post post = PostMapper.INSTANCE.toPost(postDTO);

        //post.setUser( ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()) );
        post.setCommunity(community);

        postRepository.save(post);

        return new ResponseEntity<>(EntityModel.of(postDTO).add(linkTo(CommunityController.class).slash(community.getName()).slash(post.getId()).withSelfRel()), HttpStatus.CREATED);
    }

    public ResponseEntity<EntityModel<Post>> findPostById(int postId)
    {
        Optional<Post> post = postRepository.findById(postId);

        if(post.isEmpty())
        {
            throw new NotFoundException("Post with id '" + postId + "' does not exist.");
        }

        return new ResponseEntity<>(
                EntityModel.of(post.get()).add(linkTo(CommunityController.class).slash("posts").slash(post.get().getId()).withSelfRel()),
                HttpStatus.OK);
    }

    public ResponseEntity<String> deletePost(int postId)
    {
        Optional<Post> post = postRepository.findById(postId);

        if(post.isEmpty())
        {
            throw new NotFoundException("Post with id '" + postId + "' does not exist.");
        }

        postRepository.delete(post.get());

        return new ResponseEntity<>("Post with id '" + postId + "' has been deleted.", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<EntityModel<Post>> updatePost(int postId, String content)
    {
        Optional<Post> post = postRepository.findById(postId);

        if(post.isEmpty())
        {
            throw new NotFoundException("Post with id '" + postId + "' does not exist.");
        }

        post.get().setContent(content);
        postRepository.save(post.get());

        return new ResponseEntity<>(EntityModel.of(post.get()).add(linkTo(CommunityController.class).slash(post.get().getCommunity().getName()).slash(post.get().getId()).withSelfRel()), HttpStatus.OK);
    }

}
