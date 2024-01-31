package org.springprojects.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springprojects.entities.Community;
import org.springprojects.repositories.CommunityRepository;

@Service
public class CommunityService
{
    private final CommunityRepository communityRepository;

    @Autowired
    public CommunityService(CommunityRepository communityRepository)
    {
        this.communityRepository = communityRepository;
    }

    public Community findByName(String name)
    {
        return communityRepository.findByName(name);
    }

}
