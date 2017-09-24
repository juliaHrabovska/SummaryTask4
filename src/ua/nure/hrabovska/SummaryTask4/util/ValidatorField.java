package ua.nure.hrabovska.SummaryTask4.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for validating by regular expression
 *
 * @author Y. Hrabovska
 */
public class ValidatorField {
    private static final String PASSWORD_SAMPLE = "^[A-Za-z0-9]{4,20}$";
    private static final String FIRSTNAME_LASTNAME_PATRONYMIC_SAMPLE = "^[A-Za-zА-Яа-я]{2,30}$";
    private static final String EMAIL_SAMPLE = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    public static boolean isPasswordValid(String field) {
        return validateField(field, PASSWORD_SAMPLE);
    }

    public static boolean isNameFieldValid(String field) {
        return validateField(field, FIRSTNAME_LASTNAME_PATRONYMIC_SAMPLE);
    }

    public static boolean isEmailValid(String email) {
        return validateField(email, EMAIL_SAMPLE);
    }

    public static boolean isCertificate_ScoreValid(int certificate_score) {
        return certificate_score > 100 && certificate_score < 200;
    }


    /**
     * Validates string by regular expression
     *
     * @param field  string to validate
     * @param regexp pattern
     * @return true if string matches regexp, false otherwise
     */
    private static boolean validateField(String field, String regexp) {
        if (field == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(field);
        return matcher.matches();
    }
}
