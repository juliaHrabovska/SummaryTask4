package ua.nure.hrabovska.SummaryTask4.enums;

import java.util.Locale;

/**
 * Language enum
 *
 * @author Y. Hrabovska
 */
public enum Language {

    ENGLISH(new Locale("en", "US")), RUSSIAN(new Locale("ru", "Ru"));

    private Locale locale;

    private Language(Locale locale) {
        this.locale = locale;
    }

    public static Language getLanguage(int languageId) {
        if (languageId < 0 || languageId >= Language.values().length) {
            return null;
        }
        return Language.values()[languageId];
    }

    public Locale getLocale() {
        return locale;
    }
}
