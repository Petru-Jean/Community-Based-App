package org.springprojects.dto.userDTO;

import org.springprojects.entities.DateAudit;

import java.util.Date;

public class ViewUserDTO
{
    private String username;

    private final Date createdAt;

    public ViewUserDTO(String username, Date createdAt)
    {
        this.username  = username;
        this.createdAt = createdAt;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Date getCreationDate()
    {
        return createdAt;
    }


}
