import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;

public class firstAppiumTest {
    AppiumDriver driver;

    @BeforeClass
    public void beforeClass() throws Exception {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platform_name", "Android");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("avd", "Pixel_7");
        desiredCapabilities.setCapability("app", "/Users/t822554/projects/appium/apps/saucelabs/new/Android-MyDemoAppRN.1.3.0.build-244.apk");
        desiredCapabilities.setCapability("appPackage", "com.saucelabs.mydemoapp.rn");
        desiredCapabilities.setCapability("appActivity", "com.saucelabs.mydemoapp.rn.MainActivity");

        URL url = new URL("http://0.0.0.0:4723");

        driver = new AndroidDriver(url, desiredCapabilities);
        String sessionID = driver.getSessionId().toString();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void invalidLoginTest() {
        navigateToLoginScreen();

        WebElement txtUsername = (WebElement) driver.findElement(AppiumBy.accessibilityId("Username input field"));
        WebElement txtPassword = (WebElement) driver.findElement(AppiumBy.accessibilityId("Password input field"));
        WebElement btnLogin = (WebElement) driver.findElement(AppiumBy.accessibilityId("Login button"));

        txtUsername.sendKeys("bob@example.com");
        txtPassword.sendKeys("1020");
        btnLogin.click();

        WebElement errMessage = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Provided credentials do not match any user in this service.\"]"));
        String actualErr = errMessage.getAttribute("text");
        String expectedErr = "Provided credentials do not match any user in this service.";

        Assert.assertEquals(actualErr, expectedErr);
    }



    @Test
    public void validLoginTest() {
        navigateToLoginScreen();

        WebElement txtUsername = (WebElement) driver.findElement(AppiumBy.accessibilityId("Username input field"));
        WebElement txtPassword = (WebElement) driver.findElement(AppiumBy.accessibilityId("Password input field"));
        WebElement btnLogin = (WebElement) driver.findElement(AppiumBy.accessibilityId("Login button"));


        txtUsername.clear();
        txtPassword.clear();
        txtUsername.sendKeys("bob@example.com");
        txtPassword.sendKeys("10203040");
        btnLogin.click();
        WebElement loggedInProductsHeader = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Products\"]"));
        Assert.assertTrue(loggedInProductsHeader.isDisplayed());

    }

    private void navigateToLoginScreen() {
        WebElement menu = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"open menu\"]/android.widget.ImageView"));
        menu.click();
        WebElement menuLinkLogin = driver.findElement(AppiumBy.accessibilityId("menu item log in"));
        menuLinkLogin.click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


}
