package ua.pomoc.helpoffers.model.message;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.pomoc.helpoffers.exception.BusinessException;
import ua.pomoc.helpoffers.model.AbstractModel;

import java.util.Collection;
import java.util.HashMap;

@Data
@NoArgsConstructor
public class HttpMessage {

    private Object body;
    private HttpMessageStatus message;

    public HttpMessage(Object body) {
        if (!validateField(body)) {
            throw new BusinessException("Not valid JSON field: " + body.getClass());
        }
        this.body = body;
    }

    public boolean validateField(Object field) {
        return field instanceof Collection
                || field instanceof HashMap
                || field instanceof AbstractModel
                || field instanceof Number
                || field instanceof String;
    }
}
