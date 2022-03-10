package ua.pomoc.helpoffers.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ua.pomoc.helpoffers.domain.InvitePeriod;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(onConstructor = @__({@JsonCreator}))
@NoArgsConstructor(onConstructor = @__({@JsonCreator}))
public class FilterModel extends AbstractModel {
    private Long city;
    private Integer availablePlaces;
    private Boolean withAnimals;
    private InvitePeriod invitePeriod;
}
