package br.com.KevinVSantos.SimpleLibrary.handler.exception;

import br.com.KevinVSantos.SimpleLibrary.handler.AbstractException;
import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends AbstractException {


    public EntityAlreadyExistsException(){
        super("Entity already exists");
    }

    public HttpStatus getHttpStatus(){
        return HttpStatus.CONFLICT;
    }
}
