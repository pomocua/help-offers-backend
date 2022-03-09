package ua.pomoc.helpoffers.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ua.pomoc.helpoffers.domain.InvitePeriod;
import ua.pomoc.helpoffers.model.AbstractHelpOfferModel;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class HelpOfferModelResponse extends AbstractHelpOfferModel {

    private CityModel city;

    @JsonCreator
    public HelpOfferModelResponse(String fullName, String phoneNumber, CityModel city, String address, Integer availablePlaces, Boolean withAnimals, InvitePeriod invitePeriod, String comment) {
        super(fullName, phoneNumber, address, availablePlaces, withAnimals, invitePeriod, comment);
        this.city = city;
    }
}
