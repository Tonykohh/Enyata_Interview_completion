package BaseClasses;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;


public class PageBase {
    protected WebDriver driver;
    WebDriverWait wait;

    public PageBase(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public void waitForVisibility(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickAbility(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForVisibilityOfDynamicElement(By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void click(WebElement element) {
        int attempts = 0;

        while (attempts < 3) {
            try {
                waitForClickAbility(element);
                element.click();
                break; // Exit loop if action is successful
            } catch (StaleElementReferenceException e) {
                PageFactory.initElements(driver, this);
                attempts++;
            }
        }

        if (attempts == 3) {
            throw new RuntimeException("Failed to enter text after 3 attempts due to StaleElementReferenceException");
        }
    }

    public void verifyText(WebElement element, String text, String comments) {
        int attempts = 0;

        while (attempts < 3) {
            try {
                Assert.assertEquals(element.getText(), text, comments);

                break; // Exit loop if action is successful
            } catch (StaleElementReferenceException e) {
                PageFactory.initElements(driver, this);
                attempts++;
            }
        }

        if (attempts == 3) {
            throw new RuntimeException("Failed to enter text after 3 attempts due to StaleElementReferenceException");
        }
    }

    public void scrollToElement(WebElement element, int yOffset) {
        int attempts = 0;

        while (attempts < 3) {
            try {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript(
                        "const element = arguments[0];" +
                                "const offset = arguments[1];" +
                                "const rect = element.getBoundingClientRect();" +
                                "window.scrollTo({ top: rect.top + window.scrollY - offset, behavior: 'smooth' });",
                        element, yOffset
                );
                break;
            } catch (StaleElementReferenceException e) {
                PageFactory.initElements(driver, this);
                attempts++;
            }
            if (attempts == 3) {
                throw new RuntimeException("Failed to scroll after 3 attempts due to StaleElementReferenceException");
            }
        }
        sleep(3);
    }

/*
    public void scrollToElement(WebElement element, int yOffset) {
        int attempts = 0;

        while (attempts < 3) {
            try {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript(
                        "const element = arguments[0];" +
                                "const offset = arguments[1];" +
                                "const rect = element.getBoundingClientRect();" +
                                "window.scrollTo({ top: rect.top + window.scrollY - offset, behavior: 'smooth' });",
                        element, yOffset
                );
                break;
            } catch (StaleElementReferenceException e) {
                PageFactory.initElements(driver, this);
                attempts++;
            }
            if (attempts == 3) {
                throw new RuntimeException("Failed to scroll after 3 attempts due to StaleElementReferenceException");
            }
        }
        sleep(3);
    }

 */


    public void scrollToElement(WebElement element) {

        int attempts = 0;

        while (attempts < 3) {
            try {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
                break; // Exit loop if action is successful
            } catch (StaleElementReferenceException e) {
                PageFactory.initElements(driver, this);
                attempts++;
            }
            if (attempts == 3) {
                throw new RuntimeException("Failed to enter text after 3 attempts due to StaleElementReferenceException");
            }

        }
        sleep(3);
    }

    public void enterText(WebElement element, String text) {
        int attempts = 0;

        while (attempts < 3) {
            try {
                waitForVisibility(element);
                element.clear();
                element.sendKeys(text);
                break; // Exit loop if action is successful
            } catch (StaleElementReferenceException e) {
                PageFactory.initElements(driver, this);
                attempts++;
            }
        }

        if (attempts == 3) {
            throw new RuntimeException("Failed to enter text after 3 attempts due to StaleElementReferenceException");
        }
    }


    public void searchText(WebElement element, String text) {
        int attempts = 0;

        while (attempts < 3) {
            try {
                waitForVisibility(element);
                element.sendKeys(text);
                element.sendKeys(Keys.ENTER);
                break; // Exit loop if action is successful
            } catch (StaleElementReferenceException e) {
                PageFactory.initElements(driver, this);
                attempts++;
            }
        }

        if (attempts == 3) {
            throw new RuntimeException("Failed to search text after 3 attempts due to StaleElementReferenceException");
        }
    }


    public void checkLabelText(WebElement element, String text) {
        waitForVisibility(element);
        String labelText = element.getText();
        if (labelText.equals(text))
            System.out.println(text + "Label displayed");
        else
            System.out.println(text + "Label not displayed");
    }

    public void selectOptionByName(WebElement selectElement, String text) {
        Select select = new Select(selectElement);
        select.selectByVisibleText(text);
    }

    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public void actionsClass(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public boolean clickBuyDataCard(By radioButtonsLocator, By cardLocator, String headerText) {
        // 1. Collect all page controls (radio buttons)
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        List<WebElement> pageControls = wait
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(radioButtonsLocator));

        // 2. Loop through each page control
        for (WebElement control : pageControls) {
            // Click the page control to navigate
            control.click();

            // Wait for cards on this page to render
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cardLocator));

            // 3. Now poll until at least one is visible
            wait.until(driver -> {
                List<WebElement> cards = driver.findElements(cardLocator);
                return cards.stream().anyMatch(WebElement::isDisplayed);
            });

            // 4. Check if buyData card exists and is displayed
            List<WebElement> cards = driver.findElements(cardLocator);
            for (WebElement card : cards) {
                System.out.println("Checking if the h3 card is found on the page");
                if (!card.isDisplayed()) continue;

                System.out.println("h3 tag found");
                sleep(2);

                String actualText= card.getText();

                System.out.println("The actual text " + actualText);

                if (headerText.equals(actualText.trim())) {
                    System.out.println("The header text is "+ actualText);
                    System.out.println("About to click the explore button");
                    WebElement btn= card.findElement(By.xpath("following-sibling::a//button[contains(@class,'btn-explore-new')]"));
                    wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
                    System.out.println("Explore button clicked");
                    return true;
                }

            }
        }
        // Card not found on any page
        return false;
    }


}


