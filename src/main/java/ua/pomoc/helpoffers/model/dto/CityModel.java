package ua.pomoc.helpoffers.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ua.pomoc.helpoffers.model.AbstractModel;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CityModel extends AbstractModel {

    private Long id;
    private String name;

    @JsonCreator
    public CityModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
