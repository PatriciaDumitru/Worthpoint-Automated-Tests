
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_ProductAvailabilityCheckPage extends WBA_BasePage {

    //Locators
    By yourMatNumField = By.id("material_no");
    By yourMatNumLabel = By.cssSelector("#sample_order_search_stock_check > table > tbody > tr:nth-child(1) > td:nth-child(1) > label");
    By articleField = By.id("s2id_ArticleId");
    By brandField = By.id("BrandId");
    By ticketField = By.id("TicketId");
    By finishField = By.id("FinishId");
    By lengthField = By.id("LengthId");
    By shadeCodeField = By.id("s2id_shade_id");
    By quantityField = By.id("BulkOrderLineQuantity");
    By searchButton = By.id("search");
    
    public Ecomm_ProductAvailabilityCheckPage(WebDriver passedDriver) {
        super(passedDriver);
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
    
    public WebElement getFinishField() {
        return driver.findElement(finishField);
    }
    
    public WebElement getLengthField() {
        return driver.findElement(lengthField);
    }
    
    public WebElement getShadeCodeField() {
        return driver.findElement(shadeCodeField);
    }
    
    public WebElement getQuantityField() {
        return driver.findElement(quantityField);
    }
    
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }
    
    public Ecomm_ProductAvailabilityCheckPage setYourMatNum(String item) {
        CommonTask.setInputField(driver,yourMatNumField,item);
        
        //Click focus away from field to trigger autofill
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(yourMatNumLabel));
        element.click();
        
        return this;
    }
    
    public Ecomm_ProductAvailabilityCheckPage setArticle(String item) {
        CommonTask.setSearchField(driver,articleField,item);
        return this;
    }
    
    public Ecomm_ProductAvailabilityCheckPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,brandField,item);
        return this;
    }
    
    public Ecomm_ProductAvailabilityCheckPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,ticketField,item);
        return this;
    }
    
    public Ecomm_ProductAvailabilityCheckPage setFinish(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,finishField,item);
        return this;
    }
    
    public Ecomm_ProductAvailabilityCheckPage setLength(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,lengthField,item);
        return this;
    }
    
    public Ecomm_ProductAvailabilityCheckPage setShadeCode(String item) {
        CommonTask.setSearchField(driver,shadeCodeField,item);
        return this;
    }
    
    public Ecomm_ProductAvailabilityCheckPage setQty(String item) throws InterruptedException {
        CommonTask.setInputField(driver,quantityField,item);
        return this;
    }
    
    public Ecomm_OrderViewPage pressSearch() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
        
        return new Ecomm_OrderViewPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement waitForYourMatNum = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(yourMatNumField));
        WebElement waitForArticle = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleField));
        WebElement waitForBrand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement waitForTicket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement waitForLength = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lengthField));
        WebElement waitForFinish = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(finishField));
        WebElement waitForShadeCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCodeField));
        WebElement waitForQty = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(quantityField));
        WebElement waitForButton = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Product Availability Check Page: Your Material Number field not displayed correctly",getYourMatNumField().isDisplayed());
        AssertJUnit.assertTrue("Product Availability Check Page: Article field not displayed correctly",getArticleField().isDisplayed());
        AssertJUnit.assertTrue("Product Availability Check Page: Brand field not displayed correctly",getBrandField().isDisplayed());
        AssertJUnit.assertTrue("Product Availability Check Page: Ticket field not displayed correctly",getTicketField().isDisplayed());
        AssertJUnit.assertTrue("Product Availability Check Page: Length field not displayed correctly",getLengthField().isDisplayed());
        AssertJUnit.assertTrue("Product Availability Check Page: Finish field not displayed correctly",getFinishField().isDisplayed());
        AssertJUnit.assertTrue("Product Availability Check Page: Shade Code field not displayed correctly",getShadeCodeField().isDisplayed());
        AssertJUnit.assertTrue("Product Availability Check Page: Quantity field not displayed correctly",getQuantityField().isDisplayed());
        AssertJUnit.assertTrue("Product Availability Check Page: Search button not displayed correctly",getSearchButton().isDisplayed());
    }
    
}
