package ua.pomoc.helpoffers.model.message;

import lombok.extern.slf4j.Slf4j;
import ua.pomoc.helpoffers.model.EmptyModel;

import java.util.Optional;

import static ua.pomoc.helpoffers.model.message.HttpCode.CREATED;
import static ua.pomoc.helpoffers.model.message.HttpCode.FOUND;
import static ua.pomoc.helpoffers.model.message.HttpCode.NOT_FOUND;
import static ua.pomoc.helpoffers.model.message.HttpCode.OPERATION_FAILED;
import static ua.pomoc.helpoffers.model.message.HttpCode.OPERATION_SUCCEED;
import static ua.pomoc.helpoffers.model.message.HttpCode.READING_ERROR;
import static ua.pomoc.helpoffers.model.message.HttpCode.WRITING_ERROR;

@Slf4j
public class HttpMessageProducer {

    public static HttpMessage created(Object content) {
        HttpMessage message = new HttpMessage();
        message.setMessage(new HttpMessageStatus(CREATED, CREATED.name()));
        message.setBody(content);
        return message;
    }

    public static HttpMessage found(Object content) {
        HttpMessage message = new HttpMessage();
        message.setMessage(new HttpMessageStatus(FOUND, FOUND.name()));
        message.setBody(content);
        return message;
    }

    public static HttpMessage notFound(Object content) {
        HttpMessage message = new HttpMessage();
        message.setMessage(new HttpMessageStatus(NOT_FOUND, NOT_FOUND.name()));
        message.setBody(content);
        return message;
    }

    public static HttpMessage writingError(Throwable e) {
        HttpMessage message = new HttpMessage();
        message.setMessage(new HttpMessageStatus(WRITING_ERROR, e.getMessage()));
        message.setBody(new EmptyModel());
        log.error(e.getMessage(), e);
        return message;
    }

    public static HttpMessage readingError(Throwable e) {
        HttpMessage message = new HttpMessage();
        message.setMessage(new HttpMessageStatus(READING_ERROR, e.getMessage()));
        message.setBody(new EmptyModel());
        log.error(e.getMessage(), e);
        return message;
    }

    public static HttpMessage incorrectBody(Throwable e) {
        HttpMessage message = new HttpMessage();
        message.setMessage(new HttpMessageStatus(HttpCode.INCORRECT_BODY, e.getMessage()));
        message.setBody(new EmptyModel());
        log.error(e.getMessage(), e);
        return message;
    }

    public static HttpMessage incorrectHeader(Throwable e) {
        HttpMessage message = new HttpMessage();
        message.setMessage(new HttpMessageStatus(HttpCode.INCORRECT_HEADER, e.getMessage()));
        message.setBody(new EmptyModel());
        log.error(e.getMessage(), e);
        return message;
    }

    public static HttpMessage incorrectRequest(Throwable e) {
        HttpMessage message = new HttpMessage();
        message.setMessage(new HttpMessageStatus(HttpCode.INCORRECT_REQUEST, e.getMessage()));
        message.setBody(new EmptyModel());
        log.error(e.getMessage(), e);
        return message;
    }

    public static HttpMessage operationSucceed(Optional<Object> content) {
        HttpMessage message = new HttpMessage();
        message.setMessage(new HttpMessageStatus(OPERATION_SUCCEED, OPERATION_SUCCEED.name()));
        if (content.isPresent()) {
            message.setBody(content.get());
        } else {
            message.setBody(new EmptyModel());
        }
        return message;
    }

    public static HttpMessage operationFailed(Optional<Throwable> e) {
        HttpMessage message = new HttpMessage();
        message.setBody(new EmptyModel());
        if (e.isPresent()) {
            String stackTrace = e.get().getMessage();
            message.setMessage(new HttpMessageStatus(OPERATION_FAILED, stackTrace));
            log.error(stackTrace, e);
        } else {
            message.setMessage(new HttpMessageStatus(OPERATION_FAILED, OPERATION_FAILED.name()));
        }
        return message;
    }
}
