package edu.ggc.itec4860.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NonexistantNameException extends RuntimeException
{
    NonexistantNameException(int id)
    {
	super("Name #" + id + " is not available.");
    }
}
