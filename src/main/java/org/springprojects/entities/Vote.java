package org.springprojects.entities;
import jakarta.persistence.*;
import org.springframework.data.relational.core.mapping.Column;

import java.lang.annotation.*;

@Entity
@Table(schema = "api")
public class Vote
{
    @Id
    private int id;

    @OneToOne
    @JoinColumn(name = "votable_id")
    private Votable votable;

    @Id
    @Column(value = "vote_type")
    private VoteType voteType;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public VoteType getVoteType()
    {
        return voteType;
    }

    public void setVoteType(VoteType voteType)
    {
        this.voteType = voteType;
    }

    public enum VoteType
    {
        UPVOTE,
        DOWNVOTE
    }

}
