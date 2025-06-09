package Tests;

import BaseClasses.TestBase;
import Pages.loginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static Utils.extentReports.extentTestManager.startTest;

public class SauceDemoOfficeSkipTest extends TestBase {
    loginPage login;

    public void initialiser() {
        login = new loginPage(driver);
    }

    @Test(priority = 1, description = "Sauce Demo")
    public void launchUrl_This_Is_Designed_To_Pass1(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Skip Demo");

        initialiser();
        login.enterUsername(testData.getProperty("username"));
        login.enterPassword(testData.getProperty("password"));

        login.clickLoginBtn();
//        sleep(5);

        Assert.assertEquals(login.getSwabText(), "Swag Labs");
    }

    @Test(priority = 2, description = "Sauce Demo")
    public void launchUrl_This_Is_Designed_To_Pass2(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Skip Demo");

        driver.get(testData.getProperty("baseURL"));
        login.enterUsername(testData.getProperty("username"));
        login.enterPassword(testData.getProperty("password"));

        login.clickLoginBtn();
//        sleep(5);

        Assert.assertEquals(login.getSwabText(), "Swag Labs");
    }



    @Test(priority = 3, description = "Sauce Demo")
    public void launchUrl_This_Is_Designed_To_Fail1(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Skip Demo");

        driver.get(testData.getProperty("baseURL"));

        login.enterUsername(testData.getProperty("username"));
        login.enterPassword(testData.getProperty("password"));

        login.clickLoginBtn();
//        sleep(5);

        Assert.assertEquals(login.getSwabText(), "Swag Labss");
    }
}
