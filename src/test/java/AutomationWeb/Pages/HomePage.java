package AutomationWeb.Pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHomePage() throws InterruptedException {
        driver.get("https://10pearlsuniversity.org/");
        Thread.sleep(2000);


    }

    public boolean isPageTitleCorrect() {
        String pageTitle = driver.getTitle();
        driver.manage().window().maximize();
        return pageTitle.equals("Home - 10PEARLS University");

    }

    public void clickFacebookIcon() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll to the bottom of the page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Wait for 5 seconds
        Thread.sleep(2000);

        // Click on the Facebook icon
        driver.findElement(By.xpath("(//i[@class='fab fa-facebook'])[1]")).click();
        // Wait for 5 seconds
        Thread.sleep(2000);


    }

    public void navigateBack() throws InterruptedException {
        driver.navigate().back();
        Thread.sleep(2000);
    }

    public String getCurrentUrl() {
        String currentUrl = driver.getCurrentUrl();

// Print the current URL of the page
        System.out.println("The current URL of the page is: " + currentUrl);
        return driver.getCurrentUrl();

    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void clickLoginButton() throws InterruptedException {
        driver.findElement(By.xpath("//a[normalize-space()='SIGN UP / LOGIN']")).click();
        Thread.sleep(2000);
    }

   /* public void enterUsername(String username) throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@placeholder='Enter login']")).sendKeys(username);
        Thread.sleep(2000);
    }*/

    public void enterUsername(String username) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter login']")));
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter password']")));
        passwordInput.clear();
        passwordInput.sendKeys("passwordInput");

    }

    public void clickLoginSubmitButton() throws InterruptedException {
        driver.findElement(By.xpath("//span[normalize-space()='Login']")).click();
        Thread.sleep(2000);
    }

    public boolean isErrorMessageDisplayed() {
        WebElement errorMessage = driver.findElement(By.cssSelector("#stm-lms-login > div.stm-lms-message.error"));
        return errorMessage.getText().equals("Wrong Username or Password");
    }

    public boolean pageTitle(){
        WebDriverWait wait = new WebDriverWait(driver, 10); // Wait for up to 10 seconds
        WebElement screenTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='stm_lms_user_info_top']//h1[contains(text(),'Farasat Ali Aziz')]")));
        return screenTitle.getText().equals(("Farasat Ali Aziz"));
    }


}
