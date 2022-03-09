package ua.pomoc.helpoffers.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ua.pomoc.helpoffers.config.CacheUserConfig;
import ua.pomoc.helpoffers.model.message.HttpMessage;

import static ua.pomoc.helpoffers.model.message.HttpMessageProducer.created;
import static ua.pomoc.helpoffers.model.message.HttpMessageProducer.found;
import static ua.pomoc.helpoffers.model.message.HttpMessageProducer.notFound;
import static ua.pomoc.helpoffers.model.message.HttpMessageProducer.readingError;
import static ua.pomoc.helpoffers.model.message.HttpMessageProducer.writingError;

@RestController
public class SecurityController {

    private static final String BEARER_HEADER_START = "Bearer ";

    private CacheUserConfig cacheUserConfig;

    public SecurityController(CacheUserConfig cacheUserConfig) {
        this.cacheUserConfig = cacheUserConfig;
    }

    @GetMapping("/token")
    public HttpMessage getCacheUser(@RequestHeader("Authorization") String token) {
        try {
            return found(cacheUserConfig.cache().get(token.substring(BEARER_HEADER_START.length())));
        } catch (NullPointerException e) {
            return notFound(e);
        } catch (Exception e) {
            return readingError(e);
        }
    };

    @PostMapping("/token")
    public HttpMessage newToken() {
        try {
            return created(cacheUserConfig.newUser());
        } catch (Exception e) {
            return writingError(e);
        }
    }
}
