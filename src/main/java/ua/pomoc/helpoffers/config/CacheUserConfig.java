package ua.pomoc.helpoffers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import ua.pomoc.helpoffers.model.CacheUser;
import ua.pomoc.helpoffers.model.FilterModel;
import ua.pomoc.helpoffers.model.PaginationModel;
import ua.pomoc.helpoffers.service.DatabaseCityService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class CacheUserConfig {

    private static final Long THIRTY_MINUTES = 1000L * 60L * 30L;

    private DatabaseCityService dbCityService;

    public CacheUserConfig(DatabaseCityService dbCityService) {
        this.dbCityService = dbCityService;
    }

    @Bean
    @SessionScope
    public CacheUser newUser() {
        CacheUser newUser = new CacheUser(
                UUID.randomUUID(),
                new Date().getTime() + THIRTY_MINUTES,
                new FilterModel(),
                new PaginationModel(
                        (long) dbCityService.findAll().size(),
                        1L,
                        1L
                )
        );

        cache().put(newUser.getId().toString(), newUser);
        return newUser;
    }

    @Bean
    public Map<String, CacheUser> cache() {
        return new HashMap<>();
    }
}
