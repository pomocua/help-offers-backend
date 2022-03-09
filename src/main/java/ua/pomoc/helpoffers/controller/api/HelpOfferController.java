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
import ua.pomoc.helpoffers.exception.BusinessException;
import ua.pomoc.helpoffers.mapper.GoogleFormsModelMapper;
import ua.pomoc.helpoffers.model.EmptyModel;
import ua.pomoc.helpoffers.model.dto.GoogleFormModelRequest;
import ua.pomoc.helpoffers.model.dto.HelpOfferModelRequest;
import ua.pomoc.helpoffers.model.dto.HelpOfferModelResponse;
import ua.pomoc.helpoffers.model.PaginationModel;
import ua.pomoc.helpoffers.model.message.HttpMessage;
import ua.pomoc.helpoffers.model.message.HttpMessageParametrized;
import ua.pomoc.helpoffers.model.message.HttpCode;
import ua.pomoc.helpoffers.model.message.HttpMessageStatus;
import ua.pomoc.helpoffers.service.DatabaseCityService;
import ua.pomoc.helpoffers.service.DatabaseHelpOfferService;
import ua.pomoc.helpoffers.service.DefaultHelpOfferService;

import javax.persistence.EntityNotFoundException;
import java.nio.BufferOverflowException;
import java.util.List;
import java.util.Optional;

import static ua.pomoc.helpoffers.mapper.HelpOfferModelMapper.MAPPER;
import static ua.pomoc.helpoffers.model.message.HttpMessageProducer.created;
import static ua.pomoc.helpoffers.model.message.HttpMessageProducer.found;
import static ua.pomoc.helpoffers.model.message.HttpMessageProducer.notFound;
import static ua.pomoc.helpoffers.model.message.HttpMessageProducer.operationFailed;
import static ua.pomoc.helpoffers.model.message.HttpMessageProducer.writingError;

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
            @RequestParam(required = false) Long city,
            @RequestParam(required = false) Integer availablePlaces,
            @RequestParam(required = false) Boolean withAnimals,
            @RequestParam(required = false) InvitePeriod invitePeriod,
            @RequestParam Integer perPage,
            @RequestParam Integer page
    ) {
        HttpMessageParametrized httpMessage = new HttpMessageParametrized();
        try {
            List<HelpOffer> filteredHelpOffers = dbHelpOfferService.findAllByFilter(
                    cityService.findById(city).getId(), availablePlaces, withAnimals, invitePeriod);
            List<HelpOffer> pagedHelpOffers = helpOfferService.byPage(filteredHelpOffers, perPage, page);
            httpMessage.setBody(pagedHelpOffers);
            httpMessage.setParameters(new PaginationModel((long) pagedHelpOffers.size(), (long) page, (long) perPage));
            httpMessage.setMessage(new HttpMessageStatus(HttpCode.FOUND, HttpCode.FOUND.name()));
        } catch (NullPointerException | BusinessException e) {
            return notFound(e.getMessage());
        }
        return httpMessage;
    }

    @GetMapping("/{id}")
    public HttpMessage getById(@PathVariable Long id) {
        HttpMessage httpMessage;
        try {
            HelpOfferModelResponse response = MAPPER.toResponseModel(dbHelpOfferService.findById(id));
            httpMessage = found(response);
        } catch (NullPointerException | BusinessException e) {
            httpMessage = notFound(e.getMessage());
        }
        return httpMessage;
    }

    @PostMapping
    public HttpMessage save(@RequestBody GoogleFormModelRequest googleRequest) {
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
            return created(response);
        } catch (Exception e) {
            return writingError(e);
        }
    }

}
