package br.com.KevinVSantos.SimpleLibrary.domain.error;

import br.com.KevinVSantos.SimpleLibrary.handler.AbstractException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
public class DefaultErrorDto {

    public Date dateTime;

    public String message;

    public HttpStatus httpStatus;

    public Integer httpStatusCode;

    public String requestUrl;

    public DefaultErrorDto(AbstractException e, HttpServletRequest request){

        this.dateTime = new Date();

        this.requestUrl = request.getRequestURL().toString();

        this.message = e.getMessage();
        this.httpStatus = e.getHttpStatus();
        this.httpStatusCode = e.getHttpStatus().value();

    }

}
