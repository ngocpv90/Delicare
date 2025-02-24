package com.app.delicare.component;
import com.app.delicare.utils.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;
@Component
@RequiredArgsConstructor
public class MessageUtils {
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    public String getLocalizationMessage(String messageKey, Object ... params){
        HttpServletRequest request = WebUtils.getCurrentRequest();
        Locale locale = localeResolver.resolveLocale(request);
        return messageSource.getMessage(messageKey, params, locale);
    }
}
