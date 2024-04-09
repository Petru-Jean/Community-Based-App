package org.springprojects.entities;
import jakarta.persistence.*;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;
import java.lang.annotation.*;

@Entity
@Table(schema = "api")
public class Vote implements Serializable
{
    @Id
    private int id;

    @OneToOne
    @JoinColumn(name = "votable_id")
    private Votable votable;

    @Enumerated(EnumType.STRING)
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
        Upvote,
        Downvote
    }

    @Override
    public String toString()
    {
        return "Vote{" +
                "id=" + id +
                ", votable=" + votable +
                ", voteType=" + voteType +
                '}';
    }

    public Votable getVotable()
    {
        return votable;
    }

    public void setVotable(Votable votable)
    {
        this.votable = votable;
    }
}
