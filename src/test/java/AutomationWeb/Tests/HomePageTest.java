package AutomationWeb.Tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import AutomationWeb.Pages.HomePage;

public class HomePageTest {
    private WebDriver driver;
    private HomePage homePage;
    private SoftAssert softAssert;

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

    }

    @Test(priority = 2, dependsOnMethods = {"verifyHomePage"})
    public void verifyHomePageTitle() throws InterruptedException {
        homePage.navigateToHomePage();
        boolean isPageTitleCorrect = homePage.isPageTitleCorrect();
        Assert.assertTrue(isPageTitleCorrect, "Page title is not correct");
    }

    @Test(priority = 3, dependsOnMethods = {"verifyHomePage"})
    public void clickFacebookIcon()  throws InterruptedException {
        homePage.clickFacebookIcon();
        Thread.sleep(2000); // Wait for the page to load
        //softAssert.assertTrue(homePage.getCurrentUrl().contains("https://www.facebook.com/10PearlsUniversity/"));
        homePage.navigateBack();
        softAssert.assertTrue(homePage.getCurrentUrl().contains("https://10pearlsuniversity.org/"));
    }


    @AfterClass
    public void tearDown() {
        homePage.refreshPage();
        driver.quit();
    }
}
