package ua.nure.hrabovska.SummaryTask4.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class CaptchaVerifyTest {
    @Test
    public void verifyCaptcha() throws Exception {
        assertFalse(CaptchaVerify.verifyCaptcha("some random captcha"));
    }
}