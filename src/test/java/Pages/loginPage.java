package Pages;

import BaseClasses.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class loginPage extends PageBase {
    public loginPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement login;

    @FindBy(xpath = "//div[@class='app_logo' and normalize-space()='Swag Labs']")
    private WebElement swabText;

    public void enterUsername(String user) {
        click(username);
        enterText(username, user);
    }

    public void enterPassword(String passwrd) {
        click(password);
        enterText(password, passwrd);
    }

    public void clickLoginBtn() {
        click(login);
    }

    public String getSwabText() {
        return swabText.getText();
    }
}
