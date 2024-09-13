package br.com.KevinVSantos.SimpleLibrary.handler.exception;

import br.com.KevinVSantos.SimpleLibrary.handler.AbstractException;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends AbstractException {


    public ForbiddenException(){
        super("Forbidden");
    }

    public HttpStatus getHttpStatus(){
        return HttpStatus.FORBIDDEN;
    }
}
