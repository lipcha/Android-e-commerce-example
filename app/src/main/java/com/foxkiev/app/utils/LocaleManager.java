package com.foxkiev.app.utils;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LocaleManager {

    public static Context setLocale(Context context, String language) {
        final Locale locale = new Locale(language);
        Locale.setDefault(locale);

        final Resources res = context.getResources();
        final Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        context = context.createConfigurationContext(config);
        return context;
    }
}
