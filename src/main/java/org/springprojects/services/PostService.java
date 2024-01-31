package org.springprojects.services;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springprojects.dto.PostDTO;
import org.springprojects.entities.Community;
import org.springprojects.entities.Post;
import org.springprojects.mapper.PostMapper;
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

    public List<PostDTO> getPostsByCommunityName(String name, int pageNumber, HttpServletResponse response)
    {
        Community community = communityRepository.findByName(name);

        if(community == null)
        {
            response.setStatus(ExpiresFilter.XHttpServletResponse.SC_NOT_FOUND);

            return List.of();
        }

        pageNumber = Math.max(pageNumber, 0);

        Pageable pageable = PageRequest.of(pageNumber, 25);

        return PostMapper.INSTANCE.map(
                postRepository.findAllByCommunityId(
                community.getId(),
                pageable));
    }

}
