package com.iucosoft.mylinksspringboot.config;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public final class Constants {

    public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    public static final Set<Locale> LOCALES = Collections.unmodifiableSet(
            new HashSet<Locale>() {{
                add(new Locale("en"));
                add(new Locale("ro"));
                add(new Locale("ru"));
            }}
    );

    private Constants() {
    }
}