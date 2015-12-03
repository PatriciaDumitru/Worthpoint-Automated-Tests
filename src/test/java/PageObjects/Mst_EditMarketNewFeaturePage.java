
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditMarketNewFeaturePage extends WBA_BasePage {
    
    By featureTitleField = By.cssSelector("#MarketingNewfeatureFeatureTitle");
    By salesOrgField = By.id("s2id_MarketingNewfeatureSalesOrgId");
    By setToAllButton = By.cssSelector("#MarketingNewfeatureEditForm > div:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(2) > div.allbutton > ul > li:nth-child(1) > a");
    By featureDescField = By.id("cke_MarketingNewfeatureFeatureDescription");
    By accessTypeField = By.id("MarketingNewfeatureAccessTypeId");
    By availDateField = By.id("MarketingNewfeatureAvailabelon");
    By saveButton = By.cssSelector("#MarketingNewfeatureEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#MarketingNewfeatureEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditMarketNewFeaturePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditMarketNewFeaturePage setFeatureTitle(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(featureTitleField));
        element.clear();
        
        CommonTask.setInputField(driver,featureTitleField,item);
        
        return new Mst_EditMarketNewFeaturePage(driver);
    }
    
    public Mst_MarketNewFeaturesPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_MarketNewFeaturesPage(driver);
    }
    
    public void checkFields() {
        WebElement featureTitle = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(featureTitleField));
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement setToAll = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(setToAllButton));
        WebElement featureDesc = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(featureDescField));
        WebElement accessType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(accessTypeField));
        WebElement availDate = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(availDateField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
    
        AssertJUnit.assertTrue("Edit Market New Feature Page: Feature Title field not displayed",featureTitle.isDisplayed());
        AssertJUnit.assertTrue("Edit Market New Feature Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Edit Market New Feature Page: Set To All button not displayed",setToAll.isDisplayed());
        AssertJUnit.assertTrue("Edit Market New Feature Page: Feature Description field not displayed",featureDesc.isDisplayed());
        AssertJUnit.assertTrue("Edit Market New Feature Page: Access Type field not displayed",accessType.isDisplayed());
        AssertJUnit.assertTrue("Edit Market New Feature Page: Available Date field not displayed",availDate.isDisplayed());
        AssertJUnit.assertTrue("Edit Market New Feature Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Edit Market New Feature Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(setToAllButton));
    }
    
}
