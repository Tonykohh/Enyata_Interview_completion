package Tests;

import BaseClasses.TestBase;
import Pages.addToCartPage;
import Pages.loginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static Utils.extentReports.extentTestManager.startTest;

public class SauceDemoOfficeTest1 extends TestBase {
    loginPage login;
    addToCartPage buyItem;


    public void initialiser() {
        login = new loginPage(driver);
        buyItem = new addToCartPage(driver);
    }

    @Test(priority = 1, description = "Sauce Demo")
    public void launchUrl_This_Is_Designed_To_Pass(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Pass Demo");

        initialiser();
        login.enterUsername(testData.getProperty("username"));
        login.enterPassword(testData.getProperty("password"));

        login.clickLoginBtn();

        Assert.assertEquals(login.getSwabText(), "Swag Labs");

    }

  @Test(priority = 2, description = "Sauce demo")
    public void User_can_Add_To_Cart(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Pass Demo");
        initialiser();
        buyItem.clickAddToCartBtn();
        buyItem.clickCartIcon();
        buyItem.clickCheckoutBtn();
        buyItem.enterFirstName(testData.getProperty("firstname"));
       buyItem.enterLastName(testData.getProperty("lastname"));
       buyItem.enterPostalCode(testData.getProperty("postcode"));
       buyItem.clickContinueBtn();
       buyItem.clickFinishBtn();
        sleep(7);

    }
}
