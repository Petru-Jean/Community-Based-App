package org.springprojects.exceptions;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends HandledException{

    public AlreadyExistsException(String message)
    {
        super(message, HttpStatus.CONFLICT);
    }

}
