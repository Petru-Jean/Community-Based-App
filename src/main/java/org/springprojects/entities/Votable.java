package org.springprojects.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Votable
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "api.votable_id_seq")
    @SequenceGenerator(name = "api.votable_id_seq", sequenceName = "api.votable_id_seq",  allocationSize=1)
    private int id;

    @ManyToMany
    @JoinColumn(name = "user_id")
    private User user;

}
