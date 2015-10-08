
package PageObjects;

import AutomationFramework.CommonTask;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//used when Shade Not Available record is edited
public class Ecomm_OrderInformationPage {
    
    WebDriver driver;
    
    //Locators
    By yourMatNumField = By.id("material_no");
    By articleField = By.id("s2id_ArticleId");
    By brandField = By.id("BrandName");
    By ticketField = By.id("TicketName");
    By lengthField = By.id("LengthName");
    By styleNoField = By.id("prod_style_no");
    By shadeCodeField = By.id("s2id_shade_id");
    By requiredDateField = By.id("required_date");
    By orderedQtyField = By.id("ordered_quantity");
    By finishField = By.id("FinishName");
    By otherInfoField = By.id("otherinfo");
    By submitButton = By.id("Submit");
    By cancelButton = By.id("cancel1");
    By frameLocator = By.id("TB_iframeContent");
    
    public Ecomm_OrderInformationPage(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public WebElement getYourMatNumField() {
        return driver.findElement(yourMatNumField);
    }
    
    public WebElement getArticleField() {
        return driver.findElement(articleField);
    }
    
    public WebElement getBrandField() {
        return driver.findElement(brandField);
    }
    
    public WebElement getTicketField() {
        return driver.findElement(ticketField);
    }
    
    public WebElement getLengthField() {
        return driver.findElement(lengthField);
    }
    
    public WebElement getStyleNoField() {
        return driver.findElement(styleNoField);
    }
    
    public WebElement getShadeCodeField() {
        return driver.findElement(shadeCodeField);
    }
    
    public WebElement getRequiredDateField() {
        return driver.findElement(requiredDateField);
    }
    
    public WebElement getOrderedQtyField() {
        return driver.findElement(orderedQtyField);
    }
    
    public WebElement getFinishField() {
        return driver.findElement(finishField);
    }
    
    public WebElement getOtherInfoField() {
        return driver.findElement(otherInfoField);
    }
    
    public WebElement getSubmitButton() {
        return driver.findElement(submitButton);
    }
    
    public WebElement getCancelButton() {
        return driver.findElement(cancelButton);
    }
    
    public Ecomm_ShadeOrderConfirmationPage pressSubmit() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(submitButton));
        driver.findElement(submitButton).submit();
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        return new Ecomm_ShadeOrderConfirmationPage(driver);
    }
    
    public Ecomm_ShadeOrderConfirmationPage pressCancel() {
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(cancelButton));
        driver.findElement(cancelButton).click();
        return new Ecomm_ShadeOrderConfirmationPage(driver);
    }
    
    public Ecomm_OrderInformationPage setShadeCode(String item) {
        CommonTask.setSearchField(driver,shadeCodeField,item);
        return this;
    }
    
    public Ecomm_OrderInformationPage setRequiredDate() {
        CommonTask.setDateField(driver, requiredDateField);
        return this;
    }
    
    public void checkFields() {
        //Wait for all fields to be clickable
        WebElement waitForYMN = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(yourMatNumField));
        WebElement waitForArticle = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(articleField));
        WebElement waitForBrand = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement waitForTicket = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement waitForLength = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(lengthField));
        WebElement waitForStyleNo= new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(styleNoField));
        WebElement waitForshadeCode= new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shadeCodeField));
        WebElement waitForRequiredDate= new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requiredDateField));
        WebElement waitForOrderedQty= new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderedQtyField));
        WebElement waitForFinish= new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(finishField));
        WebElement waitForOtherInfo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(otherInfoField));
        WebElement waitForSubmit = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(submitButton));
        WebElement waitForCancel = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        Assert.assertTrue("Order Information Page (overlay): Your Material Number Field not displayed correctly",getYourMatNumField().isDisplayed());
        Assert.assertTrue("Order Information Page (overlay): Article Field not displayed correctly",getArticleField().isDisplayed());
        Assert.assertTrue("Order Information Page (overlay): Brand Field not displayed correctly",getBrandField().isDisplayed());
        Assert.assertTrue("Order Information Page (overlay): Ticket Field not displayed correctly",getTicketField().isDisplayed());
        Assert.assertTrue("Order Information Page (overlay): Length Field not displayed correctly",getLengthField().isDisplayed());
        Assert.assertTrue("Order Information Page (overlay): Style No Field not displayed correctly",getStyleNoField().isDisplayed());
        Assert.assertTrue("Order Information Page (overlay): Shade Code Field not displayed correctly",getShadeCodeField().isDisplayed());
        Assert.assertTrue("Order Information Page (overlay): Required Date Field not displayed correctly",getRequiredDateField().isDisplayed());
        Assert.assertTrue("Order Information Page (overlay): Ordered Qty Field not displayed correctly",getOrderedQtyField().isDisplayed());
        Assert.assertTrue("Order Information Page (overlay): Finish Field not displayed correctly",getFinishField().isDisplayed());
        Assert.assertTrue("Order Information Page (overlay): Other Information Field not displayed correctly",getOtherInfoField().isDisplayed());
        Assert.assertTrue("Order Information Page (overlay): Submit button not displayed correctly",getSubmitButton().isDisplayed());
        Assert.assertTrue("Order Information Page (overlay): Submit button not displayed correctly",getCancelButton().isDisplayed());
    }
    
    public void switchTo() {
        WebDriver wait = new WebDriverWait(driver,10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }
    
    public void waitForContent() {
        WebElement waitForVisibility = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(frameLocator));
    }
    
    public void waitForInvisibility() {
        boolean wait = new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOfElementLocated(frameLocator));
    }
    
    public void exitView() {
        switchTo();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();
        waitForInvisibility();
    }
    
}
