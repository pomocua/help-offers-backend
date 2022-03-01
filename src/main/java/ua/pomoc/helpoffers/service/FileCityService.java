package ua.pomoc.helpoffers.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import ua.pomoc.helpoffers.domain.City;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FileCityService implements FileService<City> {

    public static final String[] HEADERS = new String[]{"city", "city_ascii", "lat", "lng", "country", "iso2", "iso3", "admin_name", "capital", "population", "id"};

    private DatabaseCityService cityService;

    public FileCityService(DatabaseCityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public List<City> findAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<City> saveAll() {
        List<City> cities = new ArrayList<>();
        try (FileReader in = new FileReader(Thread.currentThread().getContextClassLoader().getResource("worldcities.csv").getFile());
             CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withHeader(HEADERS).withFirstRecordAsHeader())) {
            for (CSVRecord record : parser.getRecords()) {
                if (record.get("country").equals("Poland")) {
                    cities.add(new City(record.get("city")));
                }
            }
            cities = cityService.saveAll(cities);
        } catch (IOException e) {
            log.error("Unable to findAll on worldCitiesCsvParser: " + e.getMessage());
        }
        return cities;
    }
}
