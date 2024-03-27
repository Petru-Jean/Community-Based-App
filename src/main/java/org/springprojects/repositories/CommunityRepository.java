package org.springprojects.repositories;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springprojects.entities.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer>
{
    public @Nonnull Page<Community>  findAll(@Nonnull Pageable pageable);

    public Community findByName(String name);

}
