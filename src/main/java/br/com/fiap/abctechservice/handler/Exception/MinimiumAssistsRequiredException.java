package br.com.fiap.abctechservice.handler.Exception;

import lombok.Getter;

@Getter
public class MinimiumAssistsRequiredException extends RuntimeException{

    private String description;

    public MinimiumAssistsRequiredException(String message, String description){
        super();
        this.description = description;
    }
}
