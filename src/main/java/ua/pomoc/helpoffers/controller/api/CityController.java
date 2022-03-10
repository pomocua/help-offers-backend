package ua.pomoc.helpoffers.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pomoc.helpoffers.model.dto.CityModel;
import ua.pomoc.helpoffers.model.message.HttpMessage;
import ua.pomoc.helpoffers.service.DatabaseCityService;

import java.util.List;
import java.util.stream.Collectors;

import static ua.pomoc.helpoffers.mapper.CityModelMapper.MAPPER;
import static ua.pomoc.helpoffers.model.message.HttpMessageProducer.found;
import static ua.pomoc.helpoffers.model.message.HttpMessageProducer.readingError;

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
        HttpMessage httpMessage;
        try {
            List<CityModel> cities = cityService.findAll().stream()
                    .map(MAPPER::toModel).collect(Collectors.toList());
            httpMessage = found(cities);
        } catch (Exception e) {
            httpMessage = readingError(e);
        }
        return httpMessage;
    }
}
