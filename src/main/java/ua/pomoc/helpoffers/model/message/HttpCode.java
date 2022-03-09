package ua.pomoc.helpoffers.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static ua.pomoc.helpoffers.model.message.HttpCode.HttpHeadCode.ERROR;
import static ua.pomoc.helpoffers.model.message.HttpCode.HttpHeadCode.SUCCESS;

/**
 * Enum describes the slice of some HTTP statuses (refer to {@link org.springframework.http.HttpStatus }) within
 * custom codes that allowed to be in the response in application. This is the details of specific {@link HttpHeadCode}
 */
public enum HttpCode {

    // internal data storing and fetching operations
    CREATED(SUCCESS),
    FOUND(SUCCESS),
    NOT_FOUND(SUCCESS),
    WRITING_ERROR(ERROR),
    READING_ERROR(ERROR),

    // requests metadata related codes
    INCORRECT_BODY(ERROR),
    INCORRECT_HEADER(ERROR),
    INCORRECT_REQUEST(ERROR),

    // other cases
    OPERATION_SUCCEED(SUCCESS),
    OPERATION_FAILED(ERROR);

    private final HttpHeadCode head;

    HttpCode(HttpHeadCode head) {
        this.head = head;
    }

    public HttpHeadCode getHead() {
        return head;
    }

    /**
     * Head of status {@link HttpCode}
     */
    enum HttpHeadCode {
        SUCCESS,
        ERROR
    }
}
