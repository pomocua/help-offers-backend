package ua.pomoc.helpoffers.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import ua.pomoc.helpoffers.service.DatabaseCityService;
import ua.pomoc.helpoffers.service.FileCityService;

@Order(0)
@Configuration
@Slf4j
public class AppInitialization implements ApplicationRunner {

    private FileCityService fileCityService;

    public AppInitialization(FileCityService fileCityService) {
        this.fileCityService = fileCityService;
    }

    @Override
    public void run(ApplicationArguments args) {
        fileCityService.saveAll();
        log.info("Initialization completed");
    }
}
