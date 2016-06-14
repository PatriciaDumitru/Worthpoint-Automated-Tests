
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

public class Mst_CabinetsPage extends WBA_BasePage {
    
    By countryNameField = By.id("s2id_filterCountrySalesOrgCountryId");
    By salesOrgField = By.id("s2id_filterSalesOrgId");
    By shipToPartyField = By.id("s2id_filterShipToPartyId");
    By cabinetCodeField = By.xpath("//*[@id=\"filterCabinetCode\"]");/*id("filterCabinetCode");*/
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newCabinetButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_CabinetsPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_CabinetsPage setCabinetCode(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cabinetCodeField));
        element.clear();
        
        CommonTask.setInputField(driver,cabinetCodeField,item);
        return new Mst_CabinetsPage(driver);
    }
    
    public Mst_CabinetsPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return new Mst_CabinetsPage(driver);
    }
    
    public Mst_CabinetsPage setShipToParty(String item) {
        CommonTask.setSearchField(driver, shipToPartyField, item);
        return new Mst_CabinetsPage(driver);
    }
    
    public Mst_CabinetsPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_CabinetsPage(driver);
    }
    
    public Mst_CabinetsPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_CabinetsPage(driver);
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
    
    public Mst_AddCabinetPage pressNewCabinet() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newCabinetButton));
        element.click();
        
        return new Mst_AddCabinetPage(driver);
    }
    
    public int getRow(String cabinet) {
        if (!checkForRecords()) {
            return -1;
        }
        
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(5) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        AssertJUnit.assertTrue("Cabinets Page: Cabinet Code column has moved, update locators",header.getText().equals("Cabinet Code"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count > 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(5)");
            WebElement cell = driver.findElement(locator);
            if (cell.getText().trim().equals(cabinet)) {
                return i;
            }
        }
        return -1;
    }
    
    public Mst_EditCabinetPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        btn.click();
        
        return new Mst_EditCabinetPage(driver);
    }
    
    public Mst_CabinetsPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        btn.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_CabinetsPage(driver);
    }
    
    public void checkFields() {
        WebElement countryName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(countryNameField));
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement shipToParty = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToPartyField));
        WebElement cabinetCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cabinetCodeField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement cabinet = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newCabinetButton));
        
        AssertJUnit.assertTrue("Cabinets Page: Country Name field not displayed",countryName.isDisplayed());
        AssertJUnit.assertTrue("Cabinets Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Cabinets Page: Ship To Party field not displayed",shipToParty.isDisplayed());
        AssertJUnit.assertTrue("Cabinets Page: Cabinet Code field not displayed",cabinetCode.isDisplayed());
        AssertJUnit.assertTrue("Cabinets Page: Search field not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Cabinets Page: Reset field not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Cabinets Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Cabinets Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Cabinets Page: New Cabinet button not displayed",cabinet.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement cabinetCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cabinetCodeField));
    }

    public void deleteCabinet(){
        int nrOfResults = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();

        if(nrOfResults > 1){
            System.out.println("Cabinet name is already used");
            System.out.println("Deleting current Cabinet...");
            pressDelete(2);
            waitForElement();
        }
        System.out.println("Cabinet cleared");
    }
}
