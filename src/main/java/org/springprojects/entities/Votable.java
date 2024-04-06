package org.springprojects.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "api")
public class Votable implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "api.votable_id_seq")
    @SequenceGenerator(name = "api.votable_id_seq", sequenceName = "api.votable_id_seq",  allocationSize=1)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "votable_id")
    private List<Vote> votes;

    public Votable()
    {

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public List<Vote> getVotes()
    {
        return votes;
    }

    public void setVotes(List<Vote> votes)
    {
        this.votes = votes;
    }
}
