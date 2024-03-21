package org.springprojects.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springprojects.entities.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer>
{
    public Community findByName(String name);
    
    
}
