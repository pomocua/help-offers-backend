package ua.pomoc.helpoffers.config;

import org.springframework.web.filter.OncePerRequestFilter;
import ua.pomoc.helpoffers.exception.BusinessException;
import ua.pomoc.helpoffers.model.CacheUser;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class TokenizedApiFilter extends OncePerRequestFilter {

    private static final String BEARER_HEADER_START = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final Long THIRTY_MINUTES = 1000L * 60L * 30L;

    private Map<String, CacheUser> cache;

    public TokenizedApiFilter(Map<String, CacheUser> cache) {
        this.cache = cache;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                         FilterChain chain) throws IOException, ServletException, BusinessException {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        if (authorization != null && authorization.startsWith(BEARER_HEADER_START)) {
            String token = authorization.substring(BEARER_HEADER_START.length());
            if (cache.containsKey(token)) {
                Date expiredAt = new Date(cache.get(token).getExpiredAt() - THIRTY_MINUTES);
                if (expiredAt.after(new Date())) {
                    throw new BusinessException("Token is expired.");
                }
            } else {
                throw new BusinessException("Token is not valid or not provided for this request.");
            }
        } else {
            throw new BusinessException("Authorization header is broken.");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}

}
