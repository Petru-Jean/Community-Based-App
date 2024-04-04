package org.springprojects.dto.userDTO;

import java.sql.Timestamp;
import java.util.Date;

public class ViewUserDTO
{
    private String username;

    private Timestamp createdAt;

    public ViewUserDTO(String username, Timestamp createdAt)
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

    public void setCreatedAt(Timestamp createdAt)
    {
        this.createdAt = createdAt;
    }

    public Timestamp getCreatedAt()
    {
        return createdAt;
    }


}
