package ua.pomoc.helpoffers.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ua.pomoc.helpoffers.exception.BusinessException;
import ua.pomoc.helpoffers.model.message.HttpMessage;

import static ua.pomoc.helpoffers.model.message.HttpMessageProducer.incorrectRequest;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(value = { BusinessException.class })
    protected HttpMessage handleException(RuntimeException e, WebRequest request) {
        return incorrectRequest(e);
    }
}
