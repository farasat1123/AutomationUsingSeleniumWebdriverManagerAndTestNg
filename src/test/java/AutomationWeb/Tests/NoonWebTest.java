package AutomationWeb.Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import AutomationWeb.Pages.NoonWebPage;


public class NoonWebTest {
    WebDriver driver;
    WebDriverWait wait;
    NoonWebPage noonWebPage;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        noonWebPage = new NoonWebPage(driver);
    }

    @Test(priority = 1)
    public void clickSamsung() throws InterruptedException {
        driver.get("https://www.noon.com/");
        noonWebPage.selectSamsungOption();
        Thread.sleep(5000); // Wait for the page to load
        //Assert.assertTrue(noonWebPage.verifyCartItemQuantity("3"), "Failed to add item to cart");
    }

    @Test(priority = 2)
    public void clickMobiles() throws InterruptedException {
        noonWebPage.clickMobilesCategory();
        Thread.sleep(5000); // Wait for the page to load
        //Assert.assertTrue(noonWebPage.verifyCartItemQuantity("3"), "Failed to add item to cart");
    }

    @Test(priority = 3)
    public void clickSamsungS22Ultra() throws InterruptedException {
        noonWebPage.selectSamsungGalaxyS22Ultra();
        Thread.sleep(5000); // Wait for the page to load
    }

    @Test(priority = 4)
    public void clickAddToCart() throws InterruptedException {
        noonWebPage.addToCart();
        Thread.sleep(5000); // Wait for the page to load
        noonWebPage.Checkout();
        //Assert.assertTrue(noonWebPage.verifyCartItemQuantity("3"), "Failed to add item to cart");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

}