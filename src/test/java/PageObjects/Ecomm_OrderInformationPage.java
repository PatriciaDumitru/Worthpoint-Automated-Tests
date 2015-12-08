
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.testng.AssertJUnit;
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
    
    public Ecomm_ShadeOrderConfirmationPage pressSubmit() {
        WebElement submit = Wait.clickable(driver,submitButton);
        submit.submit();
        Alert alert = Wait.alert(driver,DataItems.longWait);
        alert.accept();
        
        try {
            Alert alert2 = Wait.alert(driver);
            System.out.println("Alert appeared: " + alert2.getText());
            alert2.accept();
        } catch (Exception e) {
            
        }
        
        return new Ecomm_ShadeOrderConfirmationPage(driver);
    }
    
    public Ecomm_ShadeOrderConfirmationPage pressCancel() {
        WebElement cancel = Wait.clickable(driver,cancelButton);
        cancel.click();
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
        WebElement ymn = Wait.clickable(driver,yourMatNumField);
        WebElement article = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleField));
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandField));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement length = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lengthField));
        WebElement styleNo= new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(styleNoField));
        WebElement shadeCode= new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCodeField));
        WebElement requiredDate= new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requiredDateField));
        WebElement orderedQty= new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderedQtyField));
        WebElement finish= new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(finishField));
        WebElement otherInfo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(otherInfoField));
        WebElement submit = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(submitButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Order Information Page (overlay): Your Material Number Field not displayed correctly",ymn.isDisplayed());
        AssertJUnit.assertTrue("Order Information Page (overlay): Article Field not displayed correctly",article.isDisplayed());
        AssertJUnit.assertTrue("Order Information Page (overlay): Brand Field not displayed correctly",brand.isDisplayed());
        AssertJUnit.assertTrue("Order Information Page (overlay): Ticket Field not displayed correctly",ticket.isDisplayed());
        AssertJUnit.assertTrue("Order Information Page (overlay): Length Field not displayed correctly",length.isDisplayed());
        AssertJUnit.assertTrue("Order Information Page (overlay): Style No Field not displayed correctly",styleNo.isDisplayed());
        AssertJUnit.assertTrue("Order Information Page (overlay): Shade Code Field not displayed correctly",shadeCode.isDisplayed());
        AssertJUnit.assertTrue("Order Information Page (overlay): Required Date Field not displayed correctly",requiredDate.isDisplayed());
        AssertJUnit.assertTrue("Order Information Page (overlay): Ordered Qty Field not displayed correctly",orderedQty.isDisplayed());
        AssertJUnit.assertTrue("Order Information Page (overlay): Finish Field not displayed correctly",finish.isDisplayed());
        AssertJUnit.assertTrue("Order Information Page (overlay): Other Information Field not displayed correctly",otherInfo.isDisplayed());
        AssertJUnit.assertTrue("Order Information Page (overlay): Submit button not displayed correctly",submit.isDisplayed());
        AssertJUnit.assertTrue("Order Information Page (overlay): Submit button not displayed correctly",cancel.isDisplayed());
    }
    
    public void switchTo() {
        WebDriver wait = Wait.frame(driver,frameLocator);
    }
    
    public void waitForContent() {
        WebElement waitForVisibility = Wait.visible(driver,frameLocator);
    }
    
    public void waitForInvisibility() {
        boolean wait = Wait.invisible(driver,frameLocator);
    }
    
    public void exitView() {
        switchTo();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();
        waitForInvisibility();
    }
    
}
