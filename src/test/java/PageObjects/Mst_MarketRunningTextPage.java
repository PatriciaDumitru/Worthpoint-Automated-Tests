
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

public class Mst_MarketRunningTextPage extends WBA_BasePage {
    
    By salesOrgField = By.id("s2id_filterMarketingRunningtextSalesOrgId");
    By runningTextField = By.id("filterMarketingRunningtextRunningtext");
    By accessTypeField = By.id("s2id_filterMarketingRunningtextAccessTypeId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By newRunningTextButton = By.cssSelector("#content > div.actions > ul > li > a");
    
    public Mst_MarketRunningTextPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_MarketRunningTextPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return new Mst_MarketRunningTextPage(driver);
    }
    
    public Mst_MarketRunningTextPage setRunningText(String item) {
        CommonTask.setInputField(driver, runningTextField, item);
        return new Mst_MarketRunningTextPage(driver);
    }
    
    public Mst_MarketRunningTextPage setAccessType(String item) {
        CommonTask.setSearchField(driver, accessTypeField, item);
        return new Mst_MarketRunningTextPage(driver);
    }
    
    public Mst_MarketRunningTextPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_MarketRunningTextPage(driver);
    }
    
    public Mst_MarketRunningTextPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_MarketRunningTextPage(driver);
    }
    
    public Mst_AddMarketRunningTextPage pressNewRunningText() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newRunningTextButton));
        element.click();
        
        return new Mst_AddMarketRunningTextPage(driver);
    }
    
    public Mst_EditMarketRunningTextPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditMarketRunningTextPage(driver);
    }
    
    public Mst_MarketRunningTextPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_MarketRunningTextPage(driver);
    }
    
    public int getRow(String title) {
        if (!checkForRecords()) {
            return -1;
        }
        
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(2) > a");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        AssertJUnit.assertTrue("Market Running Text Page: Title column has moved, update locators",element.getText().equals("Title"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count > 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
            if (cell.getText().equals(title)) {
                return i;
            }
        }
        return -1;
    }
    
    public void checkFields() {
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement runningText = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(runningTextField));
        WebElement accessType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(accessTypeField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement newRunningText = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newRunningTextButton));
        
        AssertJUnit.assertTrue("Marketing Running Text Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Marketing Running Text Page: Running text field not displayed",runningText.isDisplayed());
        AssertJUnit.assertTrue("Marketing Running Text Page: Access Type field not displayed",accessType.isDisplayed());
        AssertJUnit.assertTrue("Marketing Running Text Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Marketing Running Text Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Marketing Running Text Page: New Running Text button not displayed",newRunningText.isDisplayed());  
    }
    
    public void waitForElement() {
        WebElement runningText = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(runningTextField));
    }

    public void deleteMrkRunningTxt(){
        int nrOfEntry = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();
        System.out.println(nrOfEntry - 1 +" Marketing Running Text matching the test criteria found ");

        for(int i = nrOfEntry;i > 1; i--)
        {
            pressDelete(2);
            setAccessType("eComm");
            setRunningText("Generated by Automated Test");
            setSalesOrg(DataItems.salesOrgID51);
            pressSearch();
            waitForElement();
        }
        System.out.println("Marketing Running Text deleted");
    }

}
