package ua.pomoc.helpoffers.model.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ua.pomoc.helpoffers.exception.BusinessException;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class HttpMessageParametrized extends HttpMessage {

    private Object parameters;

    @JsonCreator
    public HttpMessageParametrized(Object body, Object parameters) {
        super(body);
        if (!validateField(parameters)) {
            throw new BusinessException("Not valid JSON field: " + parameters);
        }
        this.parameters = parameters;
    }
}
