package org.springprojects.exceptions;

import org.springframework.http.HttpStatus;

public class HandledException extends RuntimeException{

    HttpStatus status;

    public HandledException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }


}
