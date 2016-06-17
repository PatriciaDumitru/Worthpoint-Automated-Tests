
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_LengthsPage extends WBA_BasePage {
    
    By lengthNameField = By.id("filterLengthName");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newLengthButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_LengthsPage(WebDriver driver) {     
        super(driver);    
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_LengthsPage setLengthName(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lengthNameField));
        element.clear();
        
        CommonTask.setInputField(driver, lengthNameField, item);
        return new Mst_LengthsPage(driver);
    }
    
    public Mst_LengthsPage pressSearch() {
       WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
       element.click();
       
       return new Mst_LengthsPage(driver);
    }
    
    public Mst_LengthsPage pressReset() {
       WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
       element.click();
       
       return new Mst_LengthsPage(driver);
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
    
    public Mst_AddLengthPage pressNewLength() {
       WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newLengthButton));
       element.click();
       
       return new Mst_AddLengthPage(driver);
    }
    
    public int getRow(String lengthName) {
        if (!checkForRecords()) {
            return -1;
        }
        
        By lengthHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(2) > a");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(lengthHeader));
        
        AssertJUnit.assertTrue("Lengths Page: Length column has moved, update locators",element.getText().equals("Length"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count >= 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount + 2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            
            if (cell.getText().trim().equals(lengthName)) {
                return i;
            }
            
        }
        
        return -1;  
    }
    
    public Mst_EditLengthPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditLengthPage(driver);
    }
    
    public Mst_EditLengthPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_EditLengthPage(driver);
    }
    
    public void checkFields() {
        WebElement length = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lengthNameField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newLength = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newLengthButton));
        
        AssertJUnit.assertTrue("Lengths Page: Length Name field not displayed",length.isDisplayed());
        AssertJUnit.assertTrue("Lengths Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Lengths Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Lengths Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Lengths Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Lengths Page: New Length button not displayed",newLength.isDisplayed());
        
    }
    
    public void waitForElement() {
        WebElement length = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lengthNameField));
    }

    public void deleteLength(){
        int nrOfResults = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();

        if(nrOfResults > 1){
            System.out.println("Length name is already used");
            System.out.println("Deleting current Length");
            pressDelete(2);
            waitForElement();
        }
        System.out.println("Length cleared");
    }

}
