package ua.nure.hrabovska.SummaryTask4.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

/**
 * Captcha verify service
 *
 * @author Y. Hrabovska
 */
public class CaptchaVerify {

    private static final Logger LOG = Logger.getLogger(CaptchaVerify.class);

    private static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    private static final String SECRET_KEY = "";/*deleted for security purpose*/
    private static final String SUCCESS_CODE = "\"success\": true";

    /**
     * Checks if entered captcha is right
     *
     * @param gRecaptchaResponse
     * @return
     */
    public static boolean verifyCaptcha(String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
            return false;
        }
        boolean isValid = false;
        OutputStream outStream = null;
        BufferedReader bufferedReader = null;
        try {
            URL verifyUrl = new URL(SITE_VERIFY_URL);
            HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String postParams = "secret=" + SECRET_KEY + "&response=" + gRecaptchaResponse;
            conn.setDoOutput(true);

            outStream = conn.getOutputStream();
            outStream.write(postParams.getBytes(StandardCharsets.UTF_8));

            outStream.flush();

            StringBuilder sb = new StringBuilder();
            bufferedReader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            if (sb.toString().contains(SUCCESS_CODE)) {
                isValid = true;
            }

        } catch (Exception e) {
            LOG.debug(e.getMessage(), e);
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    LOG.debug(e.getMessage(), e);
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    LOG.debug(e.getMessage(), e);
                }
            }
        }
        return isValid;
    }
}

