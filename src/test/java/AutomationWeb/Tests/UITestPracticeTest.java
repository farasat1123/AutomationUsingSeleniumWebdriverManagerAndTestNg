package AutomationWeb.Tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.File;
import java.io.IOException;


import AutomationWeb.Pages.UITestPracticePage;


public class UITestPracticeTest{
    private WebDriver driver;
    private UITestPracticePage StudentDeatilPage;

    private SoftAssert softAssert;


    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

        StudentDeatilPage = new UITestPracticePage(driver);
        softAssert = new SoftAssert();

    }


    @Test(priority = 1)
    public void UIPracticePage() {


        // Step 1: Navigate to the home page
        StudentDeatilPage.navigateToStudentDetailPage();

        // Step 2: Extract the row data and verify through assertion
        int rowCount = StudentDeatilPage.getRowCount();
        softAssert.assertEquals(rowCount, 6, "Expected row count to be 6");

        String rowData = StudentDeatilPage.getRowData(2);
        softAssert.assertTrue(rowData.contains("aaaa"), "Row data does not contain expected data");
        captureScreenshot("UIPracticePage");


    }

    @Test(priority = 3)
    public void ClickEditButton() throws InterruptedException {
        StudentDeatilPage.clickEditButton(2);
        boolean isDisplayed = StudentDeatilPage.isEditStudentFormDisplayed();
        softAssert.assertTrue(isDisplayed, "Edit Student form is not displayed");
        StudentDeatilPage.editFormData("FarasatAli","Aziz","01/01/2001 12:00:00 AM");
        StudentDeatilPage.clickSaveButton();
        captureScreenshot("ClickEditButton");
        Thread.sleep(2000); // Wait for the page to load
    }

    @Test(priority = 4)
    public void ClickDetailsButton() throws InterruptedException {
       // driver.navigate().refresh();
        StudentDeatilPage.clickDetailsButton(5);
        captureScreenshot("ClickDetailsButton");
        driver.navigate().back();
        Thread.sleep(2000); // Wait for the page to load
    }

    @Test(priority = 5)
    public void clickDeleteButton() throws InterruptedException {
        StudentDeatilPage.clickDeleteButton(3);
        driver.navigate().back();
        Thread.sleep(2000); // Wait for the page to load
        captureScreenshot("clickDeleteButton");

    }

    /*@Test(priority = 2)
    public void verifyRowData() {
        String row = StudentDeatilPage.getRowData(2);
        Assert.assertEquals(row, "1234 aaaaa 3/29/2023 12:00:00 AM EDIT DETAILS DELETE" );
        System.out.println(row);
    }*/
    @Test(priority = 6)
    public void clickDownloadButton() throws InterruptedException {
        StudentDeatilPage.navigateToWidgetsPage();
        driver.navigate().refresh();
        StudentDeatilPage.clickDownloadButton();
        Thread.sleep(5000); // Wait for the page to load
        File downloadedFile = new File("C:\\Users\\Farasat Aziz\\Downloads\\images.png");
        Assert.assertTrue(downloadedFile.exists());
        captureScreenshot("clickDownloadButton");

    }

    @AfterClass
    public void tearDown() {
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
