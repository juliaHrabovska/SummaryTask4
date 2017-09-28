package ua.nure.hrabovska.SummaryTask4.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class for translating messages according to set language
 *
 * @author Y. Hrabovska
 */
public class MessageLanguge {

    private static final String RESOURCES = "resources";

    private static ResourceBundle resourceBundle;

    public static String getMessage(String key) {
        return resourceBundle.getString(key);
    }

    public static void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(RESOURCES, locale);
    }
}
