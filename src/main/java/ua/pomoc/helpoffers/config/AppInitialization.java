package ua.pomoc.helpoffers.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ua.pomoc.helpoffers.service.FileCityService;

@Component
@Slf4j
public class AppInitialization {

    private FileCityService fileCityService;

    public AppInitialization(FileCityService fileCityService) {
        this.fileCityService = fileCityService;
    }

    @EventListener(value = ApplicationReadyEvent.class, condition = "@databaseCityService.count() == 0")
    public void init() {
        fileCityService.saveAll();
        log.info("Initialization completed");
    }
}
