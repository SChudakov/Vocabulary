package com.sschudakov.web.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SavedRequestAwareAuthenticationSuccessHandler
        extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    // но, т.к. это просто веб-сервис и его никуда перенаправлять не надо,
    // мы просто очищаем информацию об аутентификации
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {

        SavedRequest savedRequest
                = requestCache.getRequest(request, response);
        // если реквест не в кэше, очищаем аутентификацию
        if (savedRequest == null) {
            clearAuthenticationAttributes(request);
            return;
        }
        // если есть дефолтная переадресация на какой-то URL, обычно "/"
        // то удаляем реквест из кэша и чистим аутенфтикацию
        String targetUrlParam = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl()
                || (targetUrlParam != null
                && StringUtils.hasText(request.getParameter(targetUrlParam)))) {
            requestCache.removeRequest(request, response);
            clearAuthenticationAttributes(request);
            return;
        }

        clearAuthenticationAttributes(request);
    }

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}
