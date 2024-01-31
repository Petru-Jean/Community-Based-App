package org.springprojects.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springprojects.entities.Community;
import org.springprojects.entities.Post;

import java.util.List;

@Repository
public interface CommunityRepository extends org.springframework.data.repository.Repository<Community, Integer>
{
    public Community findByName(String name);
    
    
}
