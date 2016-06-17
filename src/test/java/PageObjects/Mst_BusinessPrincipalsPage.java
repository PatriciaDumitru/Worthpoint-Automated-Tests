
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_BusinessPrincipalsPage extends WBA_BasePage {
    
    By principalNo = By.id("filterBusinessPrincipalNo");
    By principalName = By.id("filterBusinessPrincipalName");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newBusinessPrincipalButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");

    By principalNoField = By.id("filterBusinessPrincipalNo");
    By lightSource1Field = By.id("s2id_filterLightSource1stId");
    
    public Mst_BusinessPrincipalsPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(DataItems.breadcrumbLocator));
    }
    
    public Mst_BusinessPrincipalsPage setPrincipalName(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(principalName));
        element.clear();
        
        CommonTask.setInputField(driver, principalName, item);
        return new Mst_BusinessPrincipalsPage(driver);
    }

    public Mst_BusinessPrincipalsPage setPrincipalNo(String item) {
        CommonTask.setInputField(driver,principalNoField,item);
        return new Mst_BusinessPrincipalsPage(driver);
    }

    public Mst_BusinessPrincipalsPage setLightSource1(String item) {
        CommonTask.setSearchField(driver,lightSource1Field,item);
        return new Mst_BusinessPrincipalsPage(driver);
    }
    
    public Mst_BusinessPrincipalsPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_BusinessPrincipalsPage(driver);
    }
    
    public Mst_ImportPage pressImport() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        element.click();
        
        return new Mst_ImportPage(driver);
    }
    
    public CCE_ExportDownloadPage pressExport() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        
        By xlsx = By.partialLinkText("XLSX");
        WebElement xlsxBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(xlsx));
        xlsxBtn.click();
                
        return new CCE_ExportDownloadPage(driver);
    }
    
    public Mst_AddBusinessPrincipalPage pressNewBusinessPrincipal() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newBusinessPrincipalButton));
        element.click();
        
        return new Mst_AddBusinessPrincipalPage(driver);
    }
    
    public Mst_EditBusinessPrincipalPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditBusinessPrincipalPage(driver);
    }
    
    public Mst_BusinessPrincipalsPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_BusinessPrincipalsPage(driver);
    }
    
    public int getRow(String principalName) {
        
        By principalHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(3) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(principalHeader));
        
        AssertJUnit.assertTrue("Business Principals Page: Principal Name column has moved, update locators",header.getText().equals("Principal Name"));
        
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = this.getRecordCount(recordField);
        
        int tableCount = (count >= 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(3)");
            WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
            if (element.getText().trim().equals(principalName)) {
                return i;
            }
        }
        return -1;
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement principalNoField = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(principalNo));
        WebElement principalNameField = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(principalName));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newBusinessPrincipal = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newBusinessPrincipalButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Business Principals Page: Principal No Field not displayed",principalNoField.isDisplayed());
        AssertJUnit.assertTrue("Business Principals Page: Principal Name Field not displayed",principalNameField.isDisplayed());
        AssertJUnit.assertTrue("Business Principals Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Business Principals Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Business Principals Page: Import button not displayed",importBtn.isDisplayed());
        AssertJUnit.assertTrue("Business Principals Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Business Principals Page: New Business Principal button not displayed",newBusinessPrincipal.isDisplayed());
        
    }
    
    public void waitForElement() {
        WebElement principalNameField = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(principalName));
    }

}
