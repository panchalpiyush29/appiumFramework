package pages;

import factory.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"open menu\"]/android.widget.ImageView")
    private WebElement menu;
    @AndroidFindBy(accessibility = "menu item log in")
    private WebElement menuLink;
    @AndroidFindBy(accessibility = "Username input field")
    private WebElement txtUsername;
    @AndroidFindBy(accessibility = "Password input field")
    private WebElement txtPassword;
    @AndroidFindBy(accessibility = "Login button")
    private WebElement btnLogin;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Provided credentials do not match any user in this service.\"]")
    private WebElement errMessage;


    public LoginPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public LoginPage openMenu() {
        click(menu);
        return this;
    }

    public LoginPage enterUsername(String username) {
        txtUsername.clear();
        sendKeys(txtUsername, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        txtPassword.clear();
        sendKeys(txtPassword, password);
        return this;
    }

    public String getErrorMessage() {
        return getAttribute(errMessage, "text");
    }

    public ProductsPage clickLogin() {
        click(btnLogin);
        return new ProductsPage();
    }

    public LoginPage navigateToLoginScreen() {
        click(menu);
        click(menuLink);
        return this;
    }
}
