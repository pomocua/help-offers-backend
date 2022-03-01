package ua.pomoc.helpoffers.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.pomoc.helpoffers.exception.BusinessException;
import ua.pomoc.helpoffers.model.message.StatusMessage;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpMessage {

    private Object content;
    private StatusMessage message;

    @JsonCreator
    public HttpMessage(Object content) {
        if (content instanceof Collection
        || content instanceof AbstractModel
        || content instanceof Number
        || content instanceof String) {
            this.content = content;
        } else {
            throw new BusinessException("Incorrect json body: 'content' field has incorrect type - " + content.getClass());
        }
    }
}
