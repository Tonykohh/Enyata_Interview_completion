package Tests;

import BaseClasses.TestBase;
import Pages.loginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static Utils.extentReports.extentTestManager.startTest;

public class SauceDemoTest extends TestBase {
    loginPage login;

    public void initialiser() {
        login = new loginPage(driver);
    }

    @Test(priority = 1, description = "Sauce Demo Fail")
    public void launchUrlFail_This_Is_Designed_To_Fail(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Fail Demo");

        initialiser();
        login.enterUsername(testData.getProperty("username"));
        login.enterPassword(testData.getProperty("password"));

        login.clickLoginBtn();
//        sleep(5);

        Assert.assertEquals(login.getSwabText(), "Swag Labss");
    }

}
