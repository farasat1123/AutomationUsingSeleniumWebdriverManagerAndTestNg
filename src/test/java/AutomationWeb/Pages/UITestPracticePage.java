package AutomationWeb.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UITestPracticePage {
    public WebDriver driver;


    // Locators
    public By tableRows = By.xpath("//table[@class='table']/tbody/tr");

    public UITestPracticePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToStudentDetailPage() {
        driver.get("http://uitestpractice.com/Students/Index");
    }

    public void navigateToWidgetsPage()
    {
        driver.get("http://uitestpractice.com/Students/Widgets");
    }

    public int getRowCount() {
        return driver.findElements(tableRows).size();
    }

    public String getRowData(int rowNumber) {
        return driver.findElement(By.xpath("//table[@class='table']/tbody/tr[" + rowNumber + "]")).getText();
    }

    public String verifyRowData(int rowNumber) {
        WebElement row = driver.findElement(By.xpath("//table[@id='grd']/tbody/tr[" + rowNumber + "]"));
        return row.getText();
    }

    public void clickEditButton(int rowNumber) {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Wait for up to 10 seconds
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='table']/tbody/tr[" + rowNumber + "]//button[contains(text(), 'EDIT')]")));
        editButton.click();
    }


    public boolean isEditStudentFormDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Wait for up to 10 seconds
        WebElement editStudentForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), 'Edit')]")));

        return editStudentForm.isDisplayed();
    }

    public void editFormData(String FirstName, String lastName, String Date) {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Wait for up to 10 seconds
        WebElement FirstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='FirstName']")));
        FirstNameField.clear();
        FirstNameField.sendKeys(FirstName);
        WebElement LastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='LastName']")));
        LastNameField.clear();
        LastNameField.sendKeys(lastName);
        WebElement DateField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='EnrollmentDate']")));
        DateField.clear();
        DateField.sendKeys(Date);
    }


    public void clickSaveButton(){
        WebDriverWait wait = new WebDriverWait(driver, 10); // Wait for up to 10 seconds
        WebElement SaveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Save']")));
        SaveButton.click();
    }

    public void clickDetailsButton(int rowNumber) {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Wait for up to 10 seconds
        WebElement detailsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='table']/tbody/tr[" + rowNumber + "]//button[contains(text(), 'DETAILS')]")));
        detailsButton.click();
    }

    public void clickDeleteButton(int rowNumber) {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Wait for up to 10 seconds
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='table']/tbody/tr[" + rowNumber + "]//button[contains(text(), 'DELETE')]")));
        deleteButton.click();
        WebElement deleteRowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Delete']")));
        deleteRowButton.click();

    }

    public void clickDownloadButton()
    {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Wait for up to 10 seconds
        WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Download a image']")));
        downloadButton.click();
    }


}
