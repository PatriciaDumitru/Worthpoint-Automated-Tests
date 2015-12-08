
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ecomm_FromExistingPage extends WBA_BasePage {
    
    WebDriver driver;
    
    //Element locators
    static By custPOFieldLocator = By.id("BulkOrderPoNumber");
    static By orderNoFieldLocator = By.cssSelector("#s2id_BulkOrderId");
    static String orderNOID = "s2id_BulkOrderId";
    static By orderNoSearchLocator = By.cssSelector("#select2-drop > div > input");
    static By orderNoResultLocator = By.cssSelector("#select2-drop > ul > li:nth-child(1)");
    static By ymnFieldLocator = By.id("material_no");
    static By articleFieldLocator = By.id("s2id_ArticleId");
    static By articleSearchLocator = By.cssSelector("#select2-drop > div > input");
    static By articleResultLocator = By.cssSelector("#select2-drop > ul > li > div > span");
    static By shadeCodeLocator = By.id("s2id_shade_id");
    static By shadeCodeSearchLocator = By.cssSelector("#s2id_shade_id > a");
    static By shadeCodeResultLocator = By.cssSelector("#select2-drop > ul > li > div > span");
    static By brandFieldLocator = By.id("BrandId");
    static By ticketFieldLocator = By.id("TicketId");
    static By finishFieldLocator = By.id("FinishId");
    static By lengthFieldLocator = By.id("LengthId");
    static By dateFromFieldLocator = By.id("BulkOrderCreatedFrom");
    static By dateToFieldLocator = By.id("BulkOrderCreatedTo");
    static By loadButtonLocator = By.id("search");
    
    public Ecomm_FromExistingPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getLoadButton() {
        //Find and return element
        return driver.findElement(loadButtonLocator);
    }
    
    public Ecomm_FromExistingPage setCustPo(String poNumber) {
        CommonTask.setInputFieldAlt(driver,"BulkOrderPoNumber",poNumber); 
        return this;
    }
    
    public Ecomm_FromExistingPage setOrderNo(String orderNo) {
        CommonTask.setSearchField(driver, custPOFieldLocator, orderNo);
        return this;
    }
    
    public Ecomm_FromExistingPage setYmn(String ymn) {
        //Wait for element to be available and enter ymn
        WebElement ymnField = Wait.clickable(driver,ymnFieldLocator);
        //Click and type ymn
        Actions clickAndType = new Actions(driver);
        clickAndType.click(ymnField).build().perform();
        clickAndType.sendKeys(ymn).build().perform();
        
        return this;
    }
    
    public Ecomm_FromExistingPage setArticle(String article) {
        //Wait for element to be available and enter article
        WebElement articleField = Wait.clickable(driver,articleFieldLocator);
        //Click and type article
        Actions clickAndType = new Actions(driver);
        clickAndType.click(articleField).build().perform();
        //Wait for search to appear
        WebElement search = Wait.visible(driver,articleSearchLocator);
        clickAndType.sendKeys(search,article).build().perform();
        //Wait for result to appear, then press enter
        WebElement result = Wait.visible(driver,articleResultLocator);
        clickAndType.sendKeys(Keys.ENTER);
        
        return this;
    }
    
    public Ecomm_FromExistingPage setShadeCode(String shadeCode) {
        //Wait for element to be available and enter shade code
        WebElement shadeField = Wait.clickable(driver,shadeCodeLocator);
        //Click and type shade code
        Actions clickAndType = new Actions(driver);
        clickAndType.click(shadeField).build().perform();
        //Wait for search to appear
        WebElement search = Wait.visible(driver,shadeCodeSearchLocator);
        clickAndType.sendKeys(search,shadeCode).build().perform();
        //Wait for result to appear, then press enter
        WebElement waitForResult = Wait.visible(driver,shadeCodeResultLocator);
        clickAndType.sendKeys(Keys.ENTER);
        
        return this;
    }
    
    public Ecomm_FromExistingPage setBrand(String brand) {
        //Wait for element to be available and enter brand
        WebElement element = Wait.clickable(driver,brandFieldLocator);
        
        Select select = new Select(element);
        element.click();
        select.selectByVisibleText(brand);
        
        return this;
    }
    
    public Ecomm_FromExistingPage setTicket(String ticket) {
        //Wait for element to be available and enter ticket
        WebElement ticketField = Wait.clickable(driver,ticketFieldLocator);
        //Click and type ticket
        Actions clickAndType = new Actions(driver);
        clickAndType.click(ticketField).build().perform();
        clickAndType.sendKeys(ticket).build().perform();
        
        return this;
    }
    
    public Ecomm_FromExistingPage setFinish(String finish) {
        //Wait for element to be available and enter finish
        WebElement element = Wait.clickable(driver,finishFieldLocator);
        //Click and type finish
        Actions clickAndType = new Actions(driver);
        clickAndType.click(element).build().perform();
        clickAndType.sendKeys(finish).build().perform();
        
        return this;
    }
    
    public Ecomm_FromExistingPage setLength(String length) {
        //Wait for element to be available and enter length
        WebElement element = Wait.clickable(driver,lengthFieldLocator);
        //Click and type length
        Actions clickAndType = new Actions(driver);
        clickAndType.click(element).build().perform();
        clickAndType.sendKeys(length).build().perform();
        
        return this;
    }
    
    public Ecomm_ManualEntryPage pressLoad() {
        Actions clickLoad = new Actions(driver);
        clickLoad.click(this.getLoadButton());
        return new Ecomm_ManualEntryPage(driver);
    }
    
    
    
}
