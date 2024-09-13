package br.com.KevinVSantos.SimpleLibrary.handler.exception;

import br.com.KevinVSantos.SimpleLibrary.handler.AbstractException;
import org.springframework.http.HttpStatus;

public class PaymentInsuficientBalanceException extends AbstractException {


    public PaymentInsuficientBalanceException(){
        super("Insuficient balance");
    }

    public HttpStatus getHttpStatus(){
        return HttpStatus.BAD_REQUEST;
    }
}
