package com.sschudakov.web.configuration;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    private static final String AUTHENTICATE_HEADER = "WWW-Authenticate";
    private static final String BASIC_REALN = "Basic realm=";
    private static final String UNAUTHORIZED = "!Unauthorized!";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.addHeader(AUTHENTICATE_HEADER, BASIC_REALN + getRealmName());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, UNAUTHORIZED);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        /**
         * Realm – the name of the secret area. The mechanism pof
         * Digest authentication uses this information of identifying
         * the secret zone. That is why this parameter should obligatory
         * be present in the header of the Authorization and match
         * to the value of realm, passed be server in the WWW-Authenticate header.
         **/
        setRealmName("EVocabulary");
        super.afterPropertiesSet();
    }
}
