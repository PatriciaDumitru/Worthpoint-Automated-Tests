
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.Wait;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Ecomm_ProductAvailabilityCheckPage extends WBA_BasePage {

    //Locators
    By yourMatNumField = By.id("material_no");
    By yourMatNumLabel = By.cssSelector("#sample_order_search_stock_check > table > tbody > tr:nth-child(1) > td:nth-child(1) > label");
    By articleField = By.id("s2id_ArticleId");
    public By brandField = By.id("BrandId");
    By ticketField = By.id("TicketId");
    By finishField = By.id("FinishId");
    By lengthField = By.id("LengthId");
    By shadeCodeField = By.id("s2id_shade_id");
    By quantityField = By.id("BulkOrderLineQuantity");
    By searchButton = By.id("search");

    public Ecomm_ProductAvailabilityCheckPage checkBrandField(String brand) throws InterruptedException {
        CommonTask.waitForFieldUpdate(driver, brandField, brand);
        return this;
    }

    public Ecomm_ProductAvailabilityCheckPage checkLenght(String brand) throws InterruptedException {
        CommonTask.waitForFieldUpdate(driver, lengthField, brand);
        return this;
    }


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
        WebElement element = Wait.visible(driver,yourMatNumLabel);
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
        WebElement search = Wait.clickable(driver,searchButton);
        search.click();
        
        return new Ecomm_OrderViewPage(driver);
    }

    public Ecomm_ProductAvailabilityCheckPage  waitForBrand() {
        Wait.textPresentInput(driver,brandField,"astra");
        return this;
    }

    public Ecomm_ProductAvailabilityCheckPage  waitForBrand2() {
        WebElement element=driver.findElement(By.id("BrandName"));
        String elementval = element.getAttribute("value");
        System.out.print(elementval);
        Wait.textPresentInput2(driver,elementval,"astra");
        return this;
    }


    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement yourMatNum = Wait.clickable(driver,yourMatNumField);
        WebElement article = Wait.clickable(driver,articleField);
        WebElement brand = Wait.clickable(driver,brandField);
        WebElement ticket = Wait.clickable(driver,ticketField);
        WebElement length = Wait.clickable(driver,lengthField);
        WebElement finish = Wait.clickable(driver,finishField);
        WebElement shadeCode = Wait.clickable(driver,shadeCodeField);
        WebElement qty = Wait.clickable(driver,quantityField);
        WebElement search = Wait.clickable(driver,searchButton);
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Product Availability Check Page: Your Material Number field not displayed correctly",yourMatNum.isDisplayed());
        AssertJUnit.assertTrue("Product Availability Check Page: Article field not displayed correctly",article.isDisplayed());
        AssertJUnit.assertTrue("Product Availability Check Page: Brand field not displayed correctly",brand.isDisplayed());
        AssertJUnit.assertTrue("Product Availability Check Page: Ticket field not displayed correctly",ticket.isDisplayed());
        AssertJUnit.assertTrue("Product Availability Check Page: Length field not displayed correctly",length.isDisplayed());
        AssertJUnit.assertTrue("Product Availability Check Page: Finish field not displayed correctly",finish.isDisplayed());
        AssertJUnit.assertTrue("Product Availability Check Page: Shade Code field not displayed correctly",shadeCode.isDisplayed());
        AssertJUnit.assertTrue("Product Availability Check Page: Quantity field not displayed correctly",qty.isDisplayed());
        AssertJUnit.assertTrue("Product Availability Check Page: Search button not displayed correctly",search.isDisplayed());
    }
    
}
