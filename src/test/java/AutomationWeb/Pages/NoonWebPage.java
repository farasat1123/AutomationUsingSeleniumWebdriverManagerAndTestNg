package AutomationWeb.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;
import java.lang.Thread;

public class NoonWebPage {

    WebDriver driver;
    WebDriverWait wait;

    public NoonWebPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    // Locators for the elements on the page
    @FindBy(xpath = "//span[normalize-space()='Electronics']")
    WebElement electronicsMenu;

    @FindBy(xpath = "//li[@class='jsx-undefined']//a[contains(@href,'/samsung/')]")
    WebElement samsungOption;

    @FindBy(xpath = "//img[@alt='/electronics-and-mobiles/mobiles-and-accessories/mobiles-20905/samsung?sort[by]=popularity&sort[dir]=desc']")
    WebElement mobilesCategory;

    //@FindBy(xpath = "//div[@data-qa='product-name' and contains(@title, 'Samsung Galaxy S23 Ultra 5G Dual SIM Phantom Black 12GB RAM 256GB  - Middle East Version')")
    @FindBy(xpath = "//div[@data-qa='product-name']//span[text()='Samsung Galaxy S22 Ultra']")
    WebElement samsungGalaxyS22Ultra;

    @FindBy(xpath = "//body/div[@id='__next']/div/section/div/div/div/div/div/div/div/span/div/div/div/div[@data-qa='cart_offer_addToCart']/div/div[3]")
    WebElement addToCartButton;

    @FindBy(xpath = "//a[@data-qa='cart-icon']")
    WebElement cartIcon;

    @FindBy(xpath = "//div[@data-qa='cart-item-details']//a[contains(text(),'Samsung Galaxy S22 Ultra')]/parent::div//following-sibling::div[@class='cart-qty']//input")
    WebElement itemQuantity;

    @FindBy(xpath = "//button[@data-qa='cart-item-update']")
    WebElement updateCartButton;

    @FindBy(xpath = "//span[@class='sc-9e5fdd11-10 kylJkH']")
    WebElement checkoutButton;
    // Method to hover on Electronics menu and click on Samsung option
    public void selectSamsungOption() {
        //Actions action = new Actions(driver);
        //action.moveToElement(electronicsMenu).perform();
       // wait.until(ExpectedConditions.elementToBeClickable(samsungOption)).click();

        Actions actions = new Actions(driver);
        actions.moveToElement(electronicsMenu).perform();

// Wait for the Samsung tile to be clickable and click on it
        WebElement samsungTile = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='jsx-undefined']//a[contains(@href,'/samsung/')]")));
        samsungTile.click();


    }


    // Method to click on Mobiles category
    public void clickMobilesCategory() {
        wait.until(ExpectedConditions.visibilityOf(mobilesCategory)).click();
    }

    // Method to select Samsung Galaxy S22 Ultra item from the list
    public void selectSamsungGalaxyS22Ultra() {
        // Find the WebElement with data-qa attribute value "product-name" and span text "Samsung Galaxy S22 Ultra"
        WebElement samsungGalaxyS22Ultra = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-qa='product-name']//span[text()='Samsung Galaxy S22 Ultra']")));

        // Scroll to the WebElement using JavascriptExecutor
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", samsungGalaxyS22Ultra);

        samsungGalaxyS22Ultra.click();
    }

    // Method to change the quantity of the item to 3 and click on Add to Cart button
    public void addToCart() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000)");

        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='selectBoxFromComponent']/div")));
        js.executeScript("arguments[0].scrollIntoView(true);", dropdownElement);
        dropdownElement.click();
       // Thread.sleep(5000);
       // By divContaining3Xpath = By.xpath("//div[@class=' css-1hwfws3' and contains(div[@class=' css-1uccc91-singleValue'], '3')]//div[@class=' css-1uccc91-singleValue']]");
       // WebElement divContaining3 = wait.until(ExpectedConditions.elementToBeClickable(divContaining3Xpath));
       // divContaining3.click();

        // Click on the third option in the dropdown
        //driver.findElement(By.xpath("//*[@id='selectBoxFromComponent']/div/div[3]")).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();

    }

    public void Checkout() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();

    }



        // Method to go to the cart and verify the item quantity
    public boolean verifyCartItemQuantity(String expectedQuantity) {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        String actualQuantity = wait.until(ExpectedConditions.visibilityOf(itemQuantity)).getAttribute("value");
        wait.until(ExpectedConditions.elementToBeClickable(updateCartButton)).click();
        return actualQuantity.equals(expectedQuantity);
    }
}
