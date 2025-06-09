package Pages;

import BaseClasses.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class addToCartPage extends PageBase {
    public addToCartPage(WebDriver driver){
        super(driver);
    }


    @FindBy(xpath = "//button[@data-test='add-to-cart-sauce-labs-backpack']")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//a[@data-test='shopping-cart-link']")
    private WebElement cartIcon;

    @FindBy(xpath = "//button[@data-test='checkout']")
    private WebElement checkoutBtn;

    @FindBy (xpath= "//*[@id=\"first-name\"]")
    private WebElement firstnameTextBox;

    @FindBy (xpath= "//*[@id=\"last-name\"]")
    private WebElement lastnameTextBox;

    @FindBy (xpath= "//*[@id=\"postal-code\"]")
    private WebElement postaCodeTextBox;

    @FindBy (xpath = "//*[@id=\"finish\"]" )
    private WebElement finishBtn;

    @FindBy (xpath = "//*[@id=\"continue\"]")
    private WebElement continueBtn;

//getters
    public WebElement getAddToCartBtn() {
        return addToCartBtn;
    }

    public WebElement getCartIcon() {
        return cartIcon;
    }

    public WebElement getCheckoutBtn() {
        return checkoutBtn;
    }

    public WebElement getFirstnameTextBox() {
        return firstnameTextBox;
    }

    public WebElement getLastnameTextBox() {
        return lastnameTextBox;
    }

    public WebElement getPostaCodeTextBox() {
        return postaCodeTextBox;
    }

    public WebElement getFinishBtn() {
        return finishBtn;
    }


//click methods
    public void clickAddToCartBtn(){
        click(addToCartBtn);
    }

    public void clickCartIcon(){
        click(cartIcon);
    }

    public void clickCheckoutBtn(){
        click(checkoutBtn);
    }

    public void clickFinishBtn(){
        click(finishBtn);
    }

    public void clickContinueBtn(){
        click(continueBtn);
    }

//enter text methods
    public void enterFirstName(String text){
        click(firstnameTextBox);
        enterText(firstnameTextBox, text);
    }
    public void enterLastName(String text){
        click(lastnameTextBox);
        enterText(lastnameTextBox, text);
    }
    public void enterPostalCode(String text){
        click(postaCodeTextBox);
        enterText(postaCodeTextBox, text);
    }

}
