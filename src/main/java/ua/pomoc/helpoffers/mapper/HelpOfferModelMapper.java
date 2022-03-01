package ua.pomoc.helpoffers.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.pomoc.helpoffers.domain.HelpOffer;
import ua.pomoc.helpoffers.model.CityModel;
import ua.pomoc.helpoffers.model.GoogleFormModelRequest;
import ua.pomoc.helpoffers.model.HelpOfferModelResponse;

import java.util.Collection;
import java.util.List;

@Mapper
public abstract class HelpOfferModelMapper {

    public static HelpOfferModelMapper MAPPER = Mappers.getMapper(HelpOfferModelMapper.class);

    abstract List<HelpOfferModelResponse> toResponseModel(Collection<HelpOffer> helpOffer);

    public HelpOfferModelResponse toResponseModel(HelpOffer helpOffer) {
        HelpOfferModelResponse helpOfferModel = new HelpOfferModelResponse();
        helpOfferModel.setId(helpOffer.getId());
        helpOfferModel.setFullName(helpOffer.getFullName());
        helpOfferModel.setPhoneNumber(helpOffer.getPhoneNumber());
        helpOfferModel.setCity(new CityModel(helpOffer.getCity().getId(), helpOffer.getCity().getName()));
        helpOfferModel.setAddress(helpOffer.getAddress());
        helpOfferModel.setAvailablePlaces(helpOffer.getAvailablePlaces());
        helpOfferModel.setInvitePeriod(helpOffer.getInvitePeriod());
        helpOfferModel.setWithAnimals(helpOffer.getWithAnimals());
        helpOfferModel.setComment(helpOffer.getComment());
        return helpOfferModel;
    }

}
