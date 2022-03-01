package ua.pomoc.helpoffers.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pomoc.helpoffers.model.EmptyModel;
import ua.pomoc.helpoffers.model.HttpMessage;
import ua.pomoc.helpoffers.model.message.Code;
import ua.pomoc.helpoffers.model.message.StatusMessage;
import ua.pomoc.helpoffers.service.DatabaseCityService;

import java.util.stream.Collectors;

import static ua.pomoc.helpoffers.mapper.CityModelMapper.MAPPER;

@RestController
@RequestMapping("/cities")
@Slf4j
public class CityController {

    private DatabaseCityService cityService;

    public CityController(DatabaseCityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public HttpMessage getAll() {
        HttpMessage httpMessage = new HttpMessage();
        try {
            httpMessage.setContent(cityService.findAll().stream()
                    .map(MAPPER::toResponse).collect(Collectors.toList()));
            httpMessage.setMessage(new StatusMessage(Code.SUCCESS, Code.SUCCESS.name()));
        } catch (Exception e) {
            httpMessage.setMessage(new StatusMessage(Code.ERROR, e.getMessage()));
            httpMessage.setContent(new EmptyModel());
            log.error(e.getMessage());
        }
        return httpMessage;
    }
}
