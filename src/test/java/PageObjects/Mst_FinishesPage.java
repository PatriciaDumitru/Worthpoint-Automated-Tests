/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

/**
 *
 * @author ukspjsykes
 */
public class Mst_FinishesPage extends WBA_BasePage {
    
    By finishNameField = By.id("filterFinishName");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newFinishButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_FinishesPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_FinishesPage setFinishName(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(finishNameField));
        element.clear();
        
        CommonTask.setInputField(driver, finishNameField, item);
        return new Mst_FinishesPage(driver);
    }
    
    public Mst_FinishesPage pressSearch() {
       WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
       element.click();
       
       return new Mst_FinishesPage(driver);
    }
    
    public Mst_FinishesPage pressReset() {
       WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
       element.click();
       
       return new Mst_FinishesPage(driver);
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
    
    public Mst_AddFinishPage pressNewFinish() {
       WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newFinishButton));
       element.click();
       
       return new Mst_AddFinishPage(driver);
    }
    
    public int getRow(String item) {
        if (!checkForRecords()) {
            return -1;
        }
        
        By lengthHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(2) > a");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(lengthHeader));
        
        AssertJUnit.assertTrue("Finishes Page: Finish Name column has moved, update locators",element.getText().equals("Finish Name"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count >= 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount + 2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            
            if (cell.getText().trim().equals(item)) {
                return i;
            }
            
        }
        
        return -1;  
    }
    
    public Mst_EditFinishPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditFinishPage(driver);
    }
    
    public Mst_FinishesPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_FinishesPage(driver);
    }
    
    public void checkFields() {
        WebElement finish = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(finishNameField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newFinish = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newFinishButton));
        
        AssertJUnit.assertTrue("Finishes Page: Length Name field not displayed",finish.isDisplayed());
        AssertJUnit.assertTrue("Finishes Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Finishes Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Finishes Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Finishes Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Finishes Page: New Length button not displayed",newFinish.isDisplayed());       
    }
    
    public void waitForElement() {
        WebElement length = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(finishNameField));
    }
    
}
