
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_AddMarketNewFeaturePage extends WBA_BasePage {
    
    By featureTitleField = By.id("MarketingNewfeatureFeatureTitle");
    By salesOrgField = By.id("s2id_autogen1");
    By setToAllButton = By.cssSelector("#MarketingNewfeatureAddForm > div.frm > table > tbody > tr:nth-child(3) > td:nth-child(2) > div.allbutton > ul > li:nth-child(1) > a");
    By featureDescriptionFrame = By.cssSelector("#cke_1_contents > iframe");
    By featureDescriptionField = By.cssSelector("body");
    By accessTypeField = By.id("MarketingNewfeatureAccessTypeId");
    By availDateField = By.id("MarketingNewfeatureAvailabelon");
    By saveButton = By.cssSelector("#MarketingNewfeatureAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#MarketingNewfeatureAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddMarketNewFeaturePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.clickable(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddMarketNewFeaturePage setFeatureTitle(String item) {
        CommonTask.setInputField(driver,featureTitleField,item);
        return new Mst_AddMarketNewFeaturePage(driver);
    }
    
    public Mst_AddMarketNewFeaturePage setSalesOrg(String item) {
        CommonTask.setChoiceField(driver,salesOrgField,item);
        return new Mst_AddMarketNewFeaturePage(driver);
    }
    
    public Mst_AddMarketNewFeaturePage pressSetToAll() {
        WebElement element = Wait.clickable(driver,setToAllButton);
        element.click();
        
        return new Mst_AddMarketNewFeaturePage(driver);
    }
    
    public Mst_AddMarketNewFeaturePage setFeatureDescription(String item) {
        //Set a feature description. Rich text editor requires some adjusted handling
        
        //Switch to the rich text editor frame so that the elements within can be referenced
        WebDriver d = Wait.frame(driver,featureDescriptionFrame);

        //Get the body element within the editor frame
        WebElement body = Wait.presence(driver,featureDescriptionField);
        
        //Use actions to type the text
        Actions action = new Actions(driver);
        action.moveToElement(body).build().perform();
        
        action.click().build().perform();
        
        action.sendKeys(item).build().perform();
        
        //Switch back to default content
        driver.switchTo().defaultContent();
        
        return new Mst_AddMarketNewFeaturePage(driver);
    }
    
    public Mst_AddMarketNewFeaturePage setAccessType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, accessTypeField, item);
        return new Mst_AddMarketNewFeaturePage(driver);
    }
    
    public Mst_AddMarketNewFeaturePage setDate() {
        CommonTask.setDateField(driver, availDateField);
        return new Mst_AddMarketNewFeaturePage(driver);
    }
    
    public Mst_MarketNewFeaturesPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_MarketNewFeaturesPage(driver);
    }
    
    public void checkFields() {
        WebElement featureTitle = Wait.clickable(driver,featureTitleField);
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement setToAll = Wait.clickable(driver,setToAllButton);
        WebElement accessType = Wait.clickable(driver,accessTypeField);
        WebElement availDate = Wait.clickable(driver,availDateField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Add Market New Feature Page: Feature Title field not displayed",featureTitle.isDisplayed());
        AssertJUnit.assertTrue("Add Market New Feature Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Market New Feature Page: Set To All button not displayed",setToAll.isDisplayed());
        AssertJUnit.assertTrue("Add Market New Feature Page: Access Type field not displayed",accessType.isDisplayed());
        AssertJUnit.assertTrue("Add Market New Feature Page: Available From field not displayed",availDate.isDisplayed());
        AssertJUnit.assertTrue("Add Market New Feature Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Market New Feature Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement element = Wait.clickable(driver,setToAllButton);
    }
    
}
