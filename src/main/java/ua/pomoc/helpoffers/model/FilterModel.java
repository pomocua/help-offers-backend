package ua.pomoc.helpoffers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ua.pomoc.helpoffers.domain.InvitePeriod;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FilterModel extends AbstractModel {
    Long city;
    Integer availablePlaces;
    Boolean withAnimals;
    InvitePeriod invitePeriod;
}
