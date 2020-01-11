import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class SearchTextTests {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app",
                "/Users/ap/IdeaProjects/JavaAppiumAutomation/apks/org.wikipedia_30276_apps.evozi.com.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        skipOnboarding();

    }


    @After
    public void tearDown() {

        driver.quit();

    }


    @Test
    public void checkPlaceholderInTheSearchField() {

        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input ",
                15);

        waitForElementPresent(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "'Search Wikipedia' is not shown",
                15);



    }


    private WebElement waitForElementPresent(By by, String errorMessage, int timeoutInSeconds){

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }


    private boolean waitForElementNotPresent(By by, String errorMessage, int timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));

    }


    private WebElement waitForElementAndClick(By by, String errorMessage, int timeoutInSeconds) {

        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;

    }


    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, int timeoutInSeconds) {

        WebElement element =  waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;

    }


    private void skipOnboarding(){

        String skipButtonIdLocator = "org.wikipedia:id/fragment_onboarding_skip_button";


        waitForElementAndClick(By.id(skipButtonIdLocator),
                    "Skip button cannot be clicked", 15);
        waitForElementNotPresent(By.id(skipButtonIdLocator),
                    "The Skip button is still shown", 15);
    }
}
