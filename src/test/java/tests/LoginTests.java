package tests;

import factory.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTests extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeClass
    public void beforeClass() {

    }

    @AfterClass
    public void afterClass() {

    }

    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
    }

    @AfterMethod

    public void afterMethod() {

    }

    @Test
    public void invalidLoginTest() {
        loginPage.navigateToLoginScreen();
        loginPage.enterUsername("bob@example.com");
        loginPage.enterPassword("1020");
        loginPage.clickLogin();
        String actualErr = loginPage.getErrorMessage();
        String expectedErr = "Provided credentials do not match any user in this service.";
        Assert.assertEquals(actualErr, expectedErr);
    }

    @Test
    public void validLoginTest() {
        loginPage.navigateToLoginScreen();
        loginPage.enterUsername("bob@example.com");
        loginPage.enterPassword("10203040");
        loginPage.clickLogin();
        String actualTitle = productsPage.getTitle();
        String expectedTitle = "Products";
        Assert.assertEquals(actualTitle, expectedTitle);

    }
}
