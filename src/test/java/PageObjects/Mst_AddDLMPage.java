
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_AddDLMPage extends WBA_BasePage {
    
    By salesOrgField = By.id("DyeLotMultipleSalesOrgId");
    By articleField = By.id("s2id_DyeLotMultipleArticleId");
    By brandField = By.id("DyeLotMultipleBrandId");
    By ticketField = By.id("DyeLotMultipleTicketId");
    By lengthField = By.id("DyeLotMultipleLengthId");
    By finishField = By.id("DyeLotMultipleFinishId");
    By levelField = By.id("DyeLotMultipleLevel");
    By rangeStartField = By.id("DyeLotMultipleRangeStart");
    By rangeEndField = By.id("DyeLotMultipleRangeUntil");
    By stepValueField = By.id("DyeLotMultipleStepValue");
    By searchButton = By.cssSelector("#DyeLotMultipleAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#DyeLotMultipleAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddDLMPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.clickable(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddDLMPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
        return new Mst_AddDLMPage(driver);
    }
    
    public Mst_AddDLMPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, brandField, item);
        return new Mst_AddDLMPage(driver);
    }
    
    public Mst_AddDLMPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, ticketField, item);
        return new Mst_AddDLMPage(driver);
    }
    
    public Mst_AddDLMPage setLength(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, lengthField, item);
        return new Mst_AddDLMPage(driver);
    }
    
    public Mst_AddDLMPage setFinish(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, finishField, item);
        return new Mst_AddDLMPage(driver);
    }
    
    public Mst_AddDLMPage setLevel(String item) throws InterruptedException {
        CommonTask.setInputField(driver, levelField, item);
        return new Mst_AddDLMPage(driver);
    }
    
    public Mst_AddDLMPage setRangeStart(String item) throws InterruptedException {
        CommonTask.setInputField(driver, rangeStartField, item);
        return new Mst_AddDLMPage(driver);
    }
    
    public Mst_AddDLMPage setRangeEnd(String item) throws InterruptedException {
        CommonTask.setInputField(driver, rangeEndField, item);
        return new Mst_AddDLMPage(driver);
    }
    
    public Mst_AddDLMPage setStepValue(String item) throws InterruptedException {
        CommonTask.setInputField(driver, stepValueField, item);
        return new Mst_AddDLMPage(driver);
    }
    
    public Mst_DyeLotMultiplesPage pressSave() {
        WebElement element = Wait.clickable(driver,searchButton);
        element.click();
        
        return new Mst_DyeLotMultiplesPage(driver);
    }
    
    public void checkFields() {
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement article = Wait.clickable(driver,articleField);
        WebElement brand = Wait.clickable(driver,brandField);
        WebElement ticket = Wait.clickable(driver,ticketField);
        WebElement length = Wait.clickable(driver,lengthField);
        WebElement finish = Wait.clickable(driver,finishField);
        WebElement level = Wait.clickable(driver,levelField);
        WebElement rangeStart = Wait.clickable(driver,rangeStartField);
        WebElement rangeEnd = Wait.clickable(driver,rangeEndField);
        WebElement stepValue = Wait.clickable(driver,stepValueField);
        WebElement search = Wait.clickable(driver,searchButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Dye Lot Multiple Page: Sales Org Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Dye Lot Multiple Page: Article Field not displayed",article.isDisplayed());
        AssertJUnit.assertTrue("Add Dye Lot Multiple Page: Brand Field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Add Dye Lot Multiple Page: Ticket Field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Add Dye Lot Multiple Page: Finish Field not displayed",finish.isDisplayed());
        AssertJUnit.assertTrue("Add Dye Lot Multiple Page: Level Field not displayed",level.isDisplayed());
        AssertJUnit.assertTrue("Add Dye Lot Multiple Page: Range Start Field not displayed",rangeStart.isDisplayed());
        AssertJUnit.assertTrue("Add Dye Lot Multiple Page: Range End not displayed",rangeEnd.isDisplayed());
        AssertJUnit.assertTrue("Add Dye Lot Multiple Page: Step Value not displayed",stepValue.isDisplayed());
        AssertJUnit.assertTrue("Add Dye Lot Multiple Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Add Dye Lot Multiple Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement rangeStart = Wait.clickable(driver,rangeStartField);
    }
    
}
