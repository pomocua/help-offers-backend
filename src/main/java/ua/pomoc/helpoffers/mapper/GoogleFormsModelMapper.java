package ua.pomoc.helpoffers.mapper;

import org.springframework.stereotype.Component;
import ua.pomoc.helpoffers.domain.InvitePeriod;
import ua.pomoc.helpoffers.model.BooleanType;
import ua.pomoc.helpoffers.model.dto.GoogleFormModelRequest;
import ua.pomoc.helpoffers.model.dto.HelpOfferModelRequest;
import ua.pomoc.helpoffers.service.DatabaseCityService;

@Component
public class GoogleFormsModelMapper {

    private DatabaseCityService cityService;

    public GoogleFormsModelMapper(DatabaseCityService cityService) {
        this.cityService = cityService;
    }

    public HelpOfferModelRequest toHelpOfferRequest(GoogleFormModelRequest googleRequest) {
        HelpOfferModelRequest helpOfferRequest = new HelpOfferModelRequest();
        helpOfferRequest.setFullName(googleRequest.getFullName());
        //TODO validate phone number if necessary
        helpOfferRequest.setPhoneNumber(googleRequest.getPhoneNumber());
        helpOfferRequest.setCity(cityService.findByName(googleRequest.getCity()).getId());
        helpOfferRequest.setAddress(googleRequest.getAddress());
        //TODO improve parse
        helpOfferRequest.setAvailablePlaces(Integer.valueOf(googleRequest.getAvailablePlaces()));
        //TODO parse invite period from string to enum or enable multiselect on the form
        helpOfferRequest.setInvitePeriod(InvitePeriod.valueOf(googleRequest.getInvitePeriod()));
        //TODO improve parsing with animals
        helpOfferRequest.setWithAnimals(BooleanType.getBoolean(googleRequest.getWithAnimals()));
        helpOfferRequest.setComment(googleRequest.getComment());
        return helpOfferRequest;
    }
}
