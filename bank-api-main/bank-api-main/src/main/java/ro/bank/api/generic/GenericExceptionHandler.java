package ro.bank.api.generic;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.bank.exceptions.BadRequestException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(BindException.class)
    public GenericResponse bindExceptionHandler(BindException bindException) {
        String errorMessage = bindException.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));

        return GenericResponse.from(HttpStatus.BAD_REQUEST, errorMessage);
    }

    @ExceptionHandler(BadRequestException.class)
    public GenericResponse badRequestHandler(BadRequestException badRequestException) {
        return GenericResponse.from(HttpStatus.BAD_REQUEST, badRequestException.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public GenericResponse runtimeHandler(RuntimeException runtimeException) {
        return GenericResponse.from(HttpStatus.INTERNAL_SERVER_ERROR, "System error. Retry later, please!");
    }


}
