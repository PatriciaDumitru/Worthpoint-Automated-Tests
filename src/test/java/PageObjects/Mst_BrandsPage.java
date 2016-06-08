
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

public class Mst_BrandsPage extends WBA_BasePage {
    
    By brandNameField = By.id("filterBrandName");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newBrandButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");

    public Mst_BrandsPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_BrandsPage setBrandName(String item) {
        CommonTask.setInputField(driver,brandNameField,item);
        return new Mst_BrandsPage(driver);
    }
    
    public Mst_BrandsPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        return new Mst_BrandsPage(driver);
    }
    
    public Mst_BrandsPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        return new Mst_BrandsPage(driver);
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
    
    public Mst_EditBrandPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        
        element.click();
        
        return new Mst_EditBrandPage(driver);
    }
    
    public Mst_BrandsPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_BrandsPage(driver);
    }
    
    public Mst_AddBrandPage pressNewBrand() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newBrandButton));
        element.click();
        
        return new Mst_AddBrandPage(driver);
    }
    
    public int getRow(String brand) {
        if (!checkForRecords()) {
            return -1;
        }
        
        By brandHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(2) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(brandHeader));
        
        AssertJUnit.assertTrue("Brands Page: Brand Name column has moved, update locators",header.getText().equals("Brand Name"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        
        int tableCount = (count >= 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            
            if (cell.getText().trim().equals(brand)) {
                return i;
            }
            
        }
        
        return -1;
        
    }
    
    public void checkFields() {
        WebElement brandName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandNameField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newBrand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newBrandButton));
        
        AssertJUnit.assertTrue("Brand Page: Brand name field not displayed", brandName.isDisplayed());
        AssertJUnit.assertTrue("Brand Page: Search button not displayed", search.isDisplayed());
        AssertJUnit.assertTrue("Brand Page: Reset button not displayed", reset.isDisplayed());
        AssertJUnit.assertTrue("Brand Page: Import button not displayed", importB.isDisplayed());
        AssertJUnit.assertTrue("Brand Page: Export button not displayed", export.isDisplayed());
        AssertJUnit.assertTrue("Brand Page: New Brand button not displayed", newBrand.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement brandName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandNameField));
    }

    //If the item created for testing is present, this will first delete the item , then create it
    public void deleteBrand(){
        int nrOfResults = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();

        if(nrOfResults > 1){
            System.out.println("Brand name is already used");
            System.out.println("Deleting current brand");
            pressDelete(2);
            waitForElement();
        }
        System.out.println("Brand cleared");
    }
}
