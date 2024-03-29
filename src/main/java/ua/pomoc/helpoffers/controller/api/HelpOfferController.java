package ua.pomoc.helpoffers.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.pomoc.helpoffers.domain.HelpOffer;
import ua.pomoc.helpoffers.domain.InvitePeriod;
import ua.pomoc.helpoffers.mapper.GoogleFormsModelMapper;
import ua.pomoc.helpoffers.model.EmptyModel;
import ua.pomoc.helpoffers.model.GoogleFormModelRequest;
import ua.pomoc.helpoffers.model.HelpOfferModelRequest;
import ua.pomoc.helpoffers.model.HelpOfferModelResponse;
import ua.pomoc.helpoffers.model.HttpMessage;
import ua.pomoc.helpoffers.model.message.Code;
import ua.pomoc.helpoffers.model.message.StatusMessage;
import ua.pomoc.helpoffers.service.DatabaseCityService;
import ua.pomoc.helpoffers.service.DatabaseHelpOfferService;
import ua.pomoc.helpoffers.service.DefaultHelpOfferService;

import java.util.List;

import static ua.pomoc.helpoffers.mapper.HelpOfferModelMapper.MAPPER;

@RestController
@RequestMapping("/offers")
@Slf4j
public class HelpOfferController {

    private DatabaseHelpOfferService dbHelpOfferService;
    private DefaultHelpOfferService helpOfferService;
    private DatabaseCityService cityService;
    private GoogleFormsModelMapper googleMapper;

    public HelpOfferController(DatabaseHelpOfferService dbHelpOfferService, DefaultHelpOfferService helpOfferService, DatabaseCityService cityService, GoogleFormsModelMapper googleMapper) {
        this.dbHelpOfferService = dbHelpOfferService;
        this.helpOfferService = helpOfferService;
        this.cityService = cityService;
        this.googleMapper = googleMapper;
    }

    @GetMapping
    public HttpMessage getAll(
            @RequestParam Long city,
            @RequestParam Integer availablePlaces,
            @RequestParam Boolean withAnimals,
            @RequestParam InvitePeriod invitePeriod,
            @RequestParam Integer perPage,
            @RequestParam Integer page
    ) {
        HttpMessage httpMessage = new HttpMessage();
        try {
            List<HelpOffer> filteredHelpOffers = dbHelpOfferService.findAllByCityAndAvailablePlacesAndWithAnimalsAndInvitePeriod(
                    cityService.findById(city), availablePlaces, withAnimals, invitePeriod);
            httpMessage.setContent(helpOfferService.byPage(filteredHelpOffers, perPage, page));
            httpMessage.setMessage(new StatusMessage(Code.SUCCESS, Code.SUCCESS.name()));
        } catch (Exception e) {
            httpMessage.setMessage(new StatusMessage(Code.ERROR, e.getMessage()));
            httpMessage.setContent(new EmptyModel());
            log.error(e.getMessage());
        }
        return httpMessage;

    }

    @GetMapping("/{id}")
    public HttpMessage getById(@PathVariable Long id) {
        HttpMessage httpMessage = new HttpMessage();
        try {
            httpMessage.setContent(MAPPER.toResponseModel(dbHelpOfferService.findById(id)));
            httpMessage.setMessage(new StatusMessage(Code.SUCCESS, Code.SUCCESS.name()));
        } catch (Exception e) {
            httpMessage.setMessage(new StatusMessage(Code.ERROR, e.getMessage()));
            httpMessage.setContent(new EmptyModel());
            log.error(e.getMessage());
        }
        return httpMessage;
    }

    @PostMapping
    public HttpMessage save(@RequestBody GoogleFormModelRequest googleRequest) {
        HttpMessage httpMessage = new HttpMessage();
        StatusMessage message = new StatusMessage();
        try {
            HelpOfferModelRequest request = googleMapper.toHelpOfferRequest(googleRequest);
            HelpOfferModelResponse response = MAPPER.toResponseModel(
                    dbHelpOfferService.save(new HelpOffer(
                            request.getFullName(),
                            request.getPhoneNumber(),
                            cityService.findById(request.getCity()),
                            request.getAddress(),
                            request.getAvailablePlaces(),
                            request.getWithAnimals(),
                            request.getInvitePeriod(),
                            request.getComment())));
            httpMessage.setContent(response);
            httpMessage.setMessage(new StatusMessage(Code.SUCCESS, Code.SUCCESS.name()));
        } catch (Exception e) {
            message.setCode(Code.ERROR);
            message.setDetails(e.getMessage());
            httpMessage.setContent(new EmptyModel());
            httpMessage.setMessage(message);
            log.error(e.getMessage());
        }
        return httpMessage;
    }

}
