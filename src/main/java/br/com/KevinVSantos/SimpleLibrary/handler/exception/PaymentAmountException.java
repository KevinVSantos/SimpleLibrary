package br.com.KevinVSantos.SimpleLibrary.handler.exception;

import br.com.KevinVSantos.SimpleLibrary.handler.AbstractException;
import org.springframework.http.HttpStatus;

public class PaymentAmountException extends AbstractException {


    public PaymentAmountException(){
        super("Amount must be greater than zero");
    }

    public HttpStatus getHttpStatus(){
        return HttpStatus.BAD_REQUEST;
    }
}
