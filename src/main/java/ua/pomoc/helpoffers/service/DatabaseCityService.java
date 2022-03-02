package ua.pomoc.helpoffers.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import ua.pomoc.helpoffers.domain.City;
import ua.pomoc.helpoffers.exception.BusinessException;
import ua.pomoc.helpoffers.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DatabaseCityService implements DatabaseService<City, Long> {

    private CityRepository cityRepository;

    public DatabaseCityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        for (City city : cityRepository.findAll()) {
            cities.add(city);
        }
        return cities;
    }

    @Override
    public City findById(Long key) {
        return cityRepository.findById(key).orElseGet(() -> {
            log.warn("City not found by id: " + key);
            throw new BusinessException("City not found by id: " + key);
        });
    }

    public City findByName(String name) {
        return cityRepository.findByName(name).orElseGet(() -> {
            log.warn("City not found by name: " + name);
            throw new BusinessException("City not found by name: " + name);
        });
    }

    @Override
    public City save(City type) {
        return cityRepository.save(type);
    }

    public List<City> saveAll(List<City> cities) {
        List<City> newCities = new ArrayList<>();
        for (City city : cityRepository.saveAll(cities)) {
            newCities.add(city);
        }
        return newCities;
    }

    @Override
    public void delete(City type) {
        cityRepository.delete(type);
    }

    public Long count() {
        return cityRepository.count();
    }
}
