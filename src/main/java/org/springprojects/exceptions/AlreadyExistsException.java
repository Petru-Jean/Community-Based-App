package org.springprojects.exceptions;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends RuntimeException
{
    public AlreadyExistsException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }

}
