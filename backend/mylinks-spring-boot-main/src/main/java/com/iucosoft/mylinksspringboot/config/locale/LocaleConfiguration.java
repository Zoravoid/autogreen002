package com.iucosoft.mylinksspringboot.config.locale;

import com.iucosoft.mylinksspringboot.config.Constants;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Configuration
@Log4j2
public class LocaleConfiguration implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        return new SmartLocaleResolver();
    }

    public static class SmartLocaleResolver extends AcceptHeaderLocaleResolver {
        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            if (StringUtils.isBlank(request.getHeader("Accept-Language"))) {
                return Locale.getDefault();
            }
            try {
                final List<Locale.LanguageRange> list = Locale.LanguageRange.parse(request.getHeader("Accept-Language"));
                Locale suggestedLocale = Locale.lookup(list, Constants.LOCALES);
                //If not available suggestions, return the default locale
                if (suggestedLocale == null)
                    return Constants.DEFAULT_LOCALE;
                return suggestedLocale;
            } catch (Exception e) {
                log.error(e);
                //Return default locale
                return Constants.DEFAULT_LOCALE;
            }
        }
    }
}