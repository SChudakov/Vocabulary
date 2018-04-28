package com.sschudakov.web.configuration;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // можем написать что угодно прямо в response
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "!Unauthorized!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // Realm – имя засекреченной области. механизм Digest аутентификации
        // использует эту информацию для идентификации защищённой зоны.
        // Поэтому этот параметр должен обязательно присутствовать в заголовке Authorization и совпадать
        // со значением realm, переданным сервером в заголовке WWW-Authenticate.
        setRealmName("EVocabulary");
        super.afterPropertiesSet();
    }
}
