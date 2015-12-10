
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

public class Mst_AddMarketRunningTextPage extends WBA_BasePage {
    
    By titleField = By.id("MarketingRunningtextTitle");
    By salesOrgField = By.id("s2id_autogen1");
    By runningTextField = By.id("MarketingRunningtextRunningtext");
    By availFromField = By.id("MarketingRunningtextAvailableFrom");
    By availToField = By.id("MarketingRunningtextAvailableTo");
    By accessTypeField = By.id("MarketingRunningtextAccessTypeId");
    By setToAllButton = By.cssSelector("#MarketingRunningtextAddForm > div.frm > table > tbody > tr:nth-child(3) > td:nth-child(2) > div.allbutton > ul > li:nth-child(1) > a");
    By saveButton = By.cssSelector("#MarketingRunningtextAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#MarketingRunningtextAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddMarketRunningTextPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddMarketRunningTextPage setTitle(String item) {
        CommonTask.setInputField(driver, titleField, item);
        return new Mst_AddMarketRunningTextPage(driver);
    }
    
    public Mst_AddMarketRunningTextPage setSalesOrg(String item) {
        CommonTask.setChoiceField(driver, salesOrgField, item);
        return new Mst_AddMarketRunningTextPage(driver);
    }
    
    public Mst_AddMarketRunningTextPage setRunningText(String item) {
        CommonTask.setInputField(driver, runningTextField, item);
        return new Mst_AddMarketRunningTextPage(driver);
    }
    
    public Mst_AddMarketRunningTextPage setAvailableFromDate() {
        CommonTask.setDateField(driver, availFromField);
        return new Mst_AddMarketRunningTextPage(driver);
    }
    
    public Mst_AddMarketRunningTextPage setAvailableToDate() {
        CommonTask.setDateField(driver, availToField);
        return new Mst_AddMarketRunningTextPage(driver);
    }
    
    public Mst_AddMarketRunningTextPage setAccessType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, accessTypeField, item);
        return new Mst_AddMarketRunningTextPage(driver);
    }
    
    public Mst_MarketRunningTextPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_MarketRunningTextPage(driver);
    }
    
    public void checkFields() {
        WebElement title = Wait.clickable(driver,titleField);
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement setToAll = Wait.clickable(driver,setToAllButton);
        WebElement runningText = Wait.clickable(driver,runningTextField);
        WebElement availFrom = Wait.clickable(driver,availFromField);
        WebElement availTo = Wait.clickable(driver,availToField);
        WebElement accessType = Wait.clickable(driver,accessTypeField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Market Running Text Page: Title field not displayed",title.isDisplayed());
        AssertJUnit.assertTrue("Add Market Running Text Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Market Running Text Page: Running Text field not displayed",runningText.isDisplayed());
        AssertJUnit.assertTrue("Add Market Running Text Page: Available From field not displayed",availFrom.isDisplayed());
        AssertJUnit.assertTrue("Add Market Running Text Page: Available To field not displayed",availTo.isDisplayed());
        AssertJUnit.assertTrue("Add Market Running Text Page: Access Type field not displayed",accessType.isDisplayed());
        AssertJUnit.assertTrue("Add Market Running Text Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Market Running Text Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement element = Wait.clickable(driver,titleField);
    }
    
}
