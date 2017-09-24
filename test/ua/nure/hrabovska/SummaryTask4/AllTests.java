package ua.nure.hrabovska.SummaryTask4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.nure.hrabovska.SummaryTask4.util.CaptchaVerifyTest;
import ua.nure.hrabovska.SummaryTask4.web.command.account.LoginCommandTest;


@RunWith(Suite.class)
@SuiteClasses({LoginCommandTest.class, CaptchaVerifyTest.class})
public class AllTests {

}
