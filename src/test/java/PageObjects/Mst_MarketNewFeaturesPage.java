
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_MarketNewFeaturesPage extends WBA_BasePage {
    
    By salesOrgField = By.id("s2id_filterMarketingNewfeatureSalesOrgId");
    By featureTitleField = By.id("filterMarketingNewfeatureFeatureTitle");
    By accessTypeField = By.id("s2id_filterMarketingNewfeatureAccessTypeId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By newFeatureButton = By.cssSelector("#content > div.actions > ul > li > a");
    
    public Mst_MarketNewFeaturesPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_MarketNewFeaturesPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return new Mst_MarketNewFeaturesPage(driver);
    }
    
    public Mst_MarketNewFeaturesPage setFeatureTitle(String item) {
        CommonTask.setInputField(driver, featureTitleField, item);
        return new Mst_MarketNewFeaturesPage(driver);
    }
    
    public Mst_MarketNewFeaturesPage setAccessType(String item) {
        CommonTask.setSearchField(driver, accessTypeField, item);
        return new Mst_MarketNewFeaturesPage(driver);
    }
    
    public Mst_MarketNewFeaturesPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_MarketNewFeaturesPage(driver);
    }
    
    public Mst_MarketNewFeaturesPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_MarketNewFeaturesPage(driver);
    }
    
    public Mst_AddMarketNewFeaturePage pressNewFeature() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newFeatureButton));
        element.click();
        
        return new Mst_AddMarketNewFeaturePage(driver);
    }
    
    public Mst_EditMarketNewFeaturePage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditMarketNewFeaturePage(driver);
    }
    
    public Mst_MarketNewFeaturesPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_MarketNewFeaturesPage(driver);
    }
    
    public int getRow(String title) {
        
        if (!checkForRecords()) {
            return -1;
        }
        
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(2) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        AssertJUnit.assertTrue("Market New Features Page: Feature Title column has moved, update locators",header.getText().equals("Feature Title"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count > 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (cell.getText().equals(title)) {
                return i;
            }
        }
        return -1;
    }
    
    public void checkFields() {
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement featureTitle = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(featureTitleField));
        WebElement accessType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(accessTypeField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement newFeature = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newFeatureButton));
        
        AssertJUnit.assertTrue("Marketing New Features Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Marketing New Features Page: Feature Title field not displayed",featureTitle.isDisplayed());
        AssertJUnit.assertTrue("Marketing New Features Page: Access Type field not displayed",accessType.isDisplayed());
        AssertJUnit.assertTrue("Marketing New Features Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Marketing New Features Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Marketing New Features Page: New Feature button not displayed",newFeature.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(featureTitleField));
    }
    
}