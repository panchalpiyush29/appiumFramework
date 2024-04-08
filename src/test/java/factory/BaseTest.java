package factory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import utils.TestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    protected static AppiumDriver driver;
    protected static Properties props;
    InputStream inputStream;

/*
    public BaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
*/

    @Parameters({"platformName", "avd"})
    @BeforeTest
    public void beforeTest(String platformName, String avd) throws IOException {
        try {
            props = new Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platform_name", platformName);
            desiredCapabilities.setCapability("automationName", props.getProperty("androidAutomationName"));
            desiredCapabilities.setCapability("avd", avd);

            URL appURL = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));
            desiredCapabilities.setCapability("app", appURL);
            //desiredCapabilities.setCapability("app", "/Users/t822554/projects/appium/apps/saucelabs/new/Android-MyDemoAppRN.1.3.0.build-244.apk");
            desiredCapabilities.setCapability("appPackage", props.getProperty("androidAppPackage"));
            desiredCapabilities.setCapability("appActivity", props.getProperty("androidAppActivity"));


            URL url = new URL(props.getProperty("androidAppiumURL"));

            driver = new AndroidDriver(url, desiredCapabilities);
            String sessionID = driver.getSessionId().toString();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    public void sendKeys(WebElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    public String getAttribute(WebElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
