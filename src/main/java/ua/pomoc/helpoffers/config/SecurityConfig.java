package ua.pomoc.helpoffers.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    private CacheUserConfig cacheUserConfig;

    public SecurityConfig(CacheUserConfig cacheUserConfig) {
        this.cacheUserConfig = cacheUserConfig;
    }

    @Bean
    public FilterRegistrationBean<TokenizedApiFilter> tokenizedApiFilter(){
        FilterRegistrationBean<TokenizedApiFilter> tokenizedFilter
                = new FilterRegistrationBean<>();
        tokenizedFilter.setFilter(new TokenizedApiFilter(cacheUserConfig.cache()));
        tokenizedFilter.addUrlPatterns("/api/offers");
        tokenizedFilter.addUrlPatterns("/api/cities");
        return tokenizedFilter;
    }
}
