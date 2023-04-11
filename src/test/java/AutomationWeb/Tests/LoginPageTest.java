package AutomationWeb.Tests;

import AutomationWeb.Pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPageTest {
    private WebDriver driver;
    private HomePage homePage;
    private SoftAssert softAssert;


    @BeforeSuite
    public void deletePreviousScreenshots() {
        try {
            FileUtils.cleanDirectory(new File("./screenshots/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        softAssert = new SoftAssert();

    }

    @Test(priority = 1)
    public void verifyHomePage() throws InterruptedException {
        homePage.navigateToHomePage();
        Assert.assertTrue(homePage.isPageTitleCorrect());
        softAssert.assertAll();
        captureScreenshot("verifyHomePage");

    }

    @Test(priority = 2, dependsOnMethods = {"verifyHomePage"})
    public void verifyHomePageTitle() throws InterruptedException {
        homePage.navigateToHomePage();
        boolean isPageTitleCorrect = homePage.isPageTitleCorrect();
        Assert.assertTrue(isPageTitleCorrect, "Page title is not correct");
        captureScreenshot("verifyHomePageTitle");
    }

    @Test(priority = 3, dependsOnMethods = {"verifyHomePage"})
    public void clickSignUpLoginButton() throws InterruptedException {
        Thread.sleep(2000); // Wait for the page to load
        homePage.clickLoginButton();
        Thread.sleep(2000); // Wait for the page to load
        softAssert.assertTrue(homePage.getCurrentUrl().contains("https://10pearlsuniversity.org/wp-login.php"));
        captureScreenshot("clickSignUpLoginButton");
    }

    @Test(priority = 4, dependsOnMethods = {"clickSignUpLoginButton"})
    public void Invalidlogin() throws InterruptedException {
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.moveByOffset(0, 0).perform();
        homePage.enterUsername("FarasatAliAziz");
        homePage.enterPassword("TrickyPassword789$$$");
        homePage.clickLoginSubmitButton();
        Thread.sleep(5000); // Wait for the page to load
        softAssert.assertTrue(homePage.isErrorMessageDisplayed());
        Thread.sleep(5000); // Wait for the page to load
        softAssert.assertTrue(homePage.getCurrentUrl().contains("https://10pearlsuniversity.org/dashboard/"));
        captureScreenshot("Invalidlogin");
    }

    @Test(priority = 5, dependsOnMethods = {"clickSignUpLoginButton"})
    public void validlogin() throws InterruptedException {
        homePage.refreshPage();
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.moveByOffset(0, 0).perform();
        homePage.enterUsername("farasat.aziz@10pearls.com");
        homePage.enterPassword("TrickyPassword789$$$");
        homePage.clickLoginSubmitButton();
        Thread.sleep(2000); // Wait for the page to load
        /*softAssert.assertTrue(homePage.pageTitle());
        Thread.sleep(5000); // Wait for the page to load
        softAssert.assertTrue(homePage.getCurrentUrl().contains("https://10pearlsuniversity.org/members/farasat-aziz/"));
        captureScreenshot("validlogin"); */
    }

    @AfterClass
    public void tearDown() {
        homePage.refreshPage();
        driver.quit();
    }

    private void captureScreenshot(String methodName) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        byte[] image = screenshot.getScreenshotAs(OutputType.BYTES);
        String fileName = methodName + "_" + System.currentTimeMillis() + ".png";
        try {
            FileUtils.writeByteArrayToFile(new File("./screenshots/" + fileName), image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
