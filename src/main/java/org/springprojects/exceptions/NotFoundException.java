package org.springprojects.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HandledException {

    public NotFoundException (String message)
    {
        super(message, HttpStatus.NOT_FOUND);
    }

}
