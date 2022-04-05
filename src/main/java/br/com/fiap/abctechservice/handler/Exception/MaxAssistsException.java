package br.com.fiap.abctechservice.handler.Exception;

import lombok.Getter;

@Getter
public class MaxAssistsException extends RuntimeException{
    private String description;

    public MaxAssistsException(String message, String description){
        super();
        this.description = description;
    }

}
