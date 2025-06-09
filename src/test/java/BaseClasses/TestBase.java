package BaseClasses;


//import DeviceCapabilities.DeviceCaps;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

public class TestBase {
    private String testDataFile_path = "src/test/java/Utils/testdata.properties";
    private FileInputStream fis;
    public Properties testData;
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;
    private EdgeOptions edgeOptions;
    protected static WebDriver driver;

    String device;
    String server;
    public static String port="7016";
    public static String chudyID="38957632fc0e4a1fa43face2f5785928";
    public static String tonyId="3c33433af64e4453ae3a19948f02c585";

    WebElement notificationclearBtn;
    WebElement otpElement;
    String otpText;
    String[] splitText;
    String otp;


    public TestBase() {
        loadPropFile();
    }

    private void loadPropFile() {
        testData = new Properties();
        try (FileInputStream fis = new FileInputStream(testDataFile_path)) {
            testData.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTestData(String key) {
        return testData.getProperty(key);
    }



    @BeforeClass
    public void mainSetup() {

        //Setting the web framework
        String browser = testData.getProperty("browser").toLowerCase();

        switch (browser) {
            case "chrome":
                chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                edgeOptions = new EdgeOptions();
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.get(testData.getProperty("baseURL"));
        driver.manage().window().maximize();
    }



    protected void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }
}
