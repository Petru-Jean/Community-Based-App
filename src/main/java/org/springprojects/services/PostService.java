package org.springprojects.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springprojects.controllers.CommunityController;
import org.springprojects.dto.postDTO.CreatePostDTO;
import org.springprojects.dto.postDTO.PostMapper;
import org.springprojects.dto.postDTO.ViewPostDTO;
import org.springprojects.entities.Community;
import org.springprojects.entities.Post;
import org.springprojects.entities.User;
import org.springprojects.exceptions.NotFoundException;
import org.springprojects.repositories.CommunityRepository;
import org.springprojects.repositories.PostRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class PostService
{
    private final PostRepository     postRepository;
    private final CommunityRepository communityRepository;

    @Autowired
    public PostService(PostRepository postRepository, CommunityRepository communityRepository)
    {
        this.postRepository   = postRepository;
        this.communityRepository = communityRepository;
    }

    public ResponseEntity<CollectionModel<EntityModel<ViewPostDTO>>> getPosts(String communityName, int pageNumber)
    {
        var community = communityRepository.findByName(communityName);

        if(community == null)
        {
            throw new NotFoundException("Failed to get posts because community '" + communityName + "' does not exist");
        }

        Pageable pageable = PageRequest.of(pageNumber, 25);

        List<ViewPostDTO> postDTOs = PostMapper.INSTANCE.getPostDTOs( postRepository.findAllByCommunityId(community.getId(), pageable) );

        List<EntityModel<ViewPostDTO>> ems = postDTOs.stream().map(dto ->
                EntityModel.of(dto, linkTo(CommunityController.class).slash(communityName).slash("posts").slash(dto.getExternalId()).withSelfRel())).toList();

        CollectionModel<EntityModel<ViewPostDTO>> model = CollectionModel.of(ems);
        model.add(linkTo(CommunityController.class).slash(communityName).slash("posts").withSelfRel());

        return new ResponseEntity<>(model, HttpStatus.OK);//, HttpStatus.OK);
    }

    public ResponseEntity<EntityModel<CreatePostDTO>> createPost(String communityName, CreatePostDTO postDTO)
    {
        Community community = communityRepository.findByName(communityName);

        if(community == null)
        {
            throw new NotFoundException("Post not created because the community '" + communityName + "' does not exist");
        }

        Post post = PostMapper.INSTANCE.toPost(postDTO);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        post.setUser( user );
        post.setCommunity(community);

        // Temporary solution
        post.setExternalId(UUID.randomUUID().toString().replace("-","").substring(0,8));

        Post savedPost = postRepository.save(post);
        postDTO.setCreatedAt(post.getCreatedAt());

        return new ResponseEntity<>(EntityModel.of(postDTO).add(linkTo(CommunityController.class).slash(community.getName()).slash("posts").slash(post.getExternalId()).withSelfRel()), HttpStatus.CREATED);
    }

    public ResponseEntity<EntityModel<ViewPostDTO>> getPost(String postURI)
    {
        Post post = postRepository.findByExternalId(postURI);

        if(post == null)
        {
            throw new NotFoundException("Post with uri '" + postURI + "' does not exist.");
        }

        var dto = PostMapper.INSTANCE.toViewPostDTO(post);

        post.getVotes().forEach(vote -> System.out.println(vote.toString()));

        return new ResponseEntity<>(
                EntityModel.of(dto).add(linkTo(CommunityController.class).slash(post.getCommunity().getName()).slash("posts").slash(post.getExternalId()).withSelfRel()),
                HttpStatus.OK);
    }

    /*public ResponseEntity<String> deletePost(int postId)
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
    }*/

}
