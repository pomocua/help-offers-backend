package ua.pomoc.helpoffers.model.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpMessageStatus {
    private HttpCode code;
    private String details;
}
