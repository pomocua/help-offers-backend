package ua.pomoc.helpoffers.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.pomoc.helpoffers.model.GoogleFormModelRequest;
import ua.pomoc.helpoffers.model.HelpOfferModelRequest;
import ua.pomoc.helpoffers.service.DatabaseCityService;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        componentModel = "spring")
@Component
public abstract class GoogleFormsModelMapper {

    public static GoogleFormsModelMapper MAPPER = Mappers.getMapper(GoogleFormsModelMapper.class);

    @Autowired
    protected DatabaseCityService cityService;

    public HelpOfferModelRequest toHelpOfferRequest(GoogleFormModelRequest googleRequest) {
        HelpOfferModelRequest helpOfferRequest = new HelpOfferModelRequest();
        helpOfferRequest.setFullName(googleRequest.getFullName());
        helpOfferRequest.setPhoneNumber(googleRequest.getPhoneNumber());
        helpOfferRequest.setCity(cityService.findByName(googleRequest.getCity()).getId());
        helpOfferRequest.setAvailablePlaces(googleRequest.getAvailablePlaces());
        helpOfferRequest.setInvitePeriod(googleRequest.getInvitePeriod());
        helpOfferRequest.setWithAnimals(googleRequest.getWithAnimals());
        helpOfferRequest.setComment(googleRequest.getComment());
        return helpOfferRequest;
    }
}
