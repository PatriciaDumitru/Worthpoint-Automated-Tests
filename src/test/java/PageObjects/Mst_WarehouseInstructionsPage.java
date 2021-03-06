
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

public class Mst_WarehouseInstructionsPage extends WBA_BasePage {
    
    By salesOrgField = By.id("s2id_filterSalesOrgId");
    By whsInstField = By.id("filterWarehouseInstructions");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newWhsInstButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_WarehouseInstructionsPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(DataItems.breadcrumbLocator));
    }
    
    public Mst_WarehouseInstructionsPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return new Mst_WarehouseInstructionsPage(driver);
    }
    
    public Mst_WarehouseInstructionsPage setWarehouseInstruction(String item) {        
        CommonTask.setInputField(driver, whsInstField, item);
        return new Mst_WarehouseInstructionsPage(driver);
    }
    
    public Mst_WarehouseInstructionsPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_WarehouseInstructionsPage(driver);
    }
    
    public Mst_WarehouseInstructionsPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_WarehouseInstructionsPage(driver);
    }
    
    public Mst_ImportPage pressImport() {
        WebElement element= new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
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
    
    public Mst_AddWarehouseInstructionPage pressNewWarehouseInstruction() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newWhsInstButton));
        element.click();
        
        return new Mst_AddWarehouseInstructionPage(driver);
    }
    
    public Mst_EditWarehouseInstructionPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditWarehouseInstructionPage(driver);
    }
    
    public Mst_WarehouseInstructionsPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_WarehouseInstructionsPage(driver);
    }
    
    public int getRow(String warehouseInst) {
        if (!checkForRecords()) {
            return -1;
        }
        
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(3)");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        AssertJUnit.assertTrue("Warehouse Instructions Page: Warehouse Instruction column has moved, update locators",header.getText().equals("Warehouse Instruction"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int records = getRecordCount(recordsField);
        int tableCount = (records >= 10) ? 10 : records;
        
        for (int i = 2; i < (tableCount+2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(3)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            
            if (cell.getText().equals(warehouseInst)) {
                return i;
            }       
        }
        
        return -1;
    }
    
    public void checkFields() {
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement whsInst = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(whsInstField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newWhsInst = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newWhsInstButton));
    
        AssertJUnit.assertTrue("Warehouse Instructions Page: Sales Organisation field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Warehouse Instructions Page: Warehouse Instruction field not displayed",whsInst.isDisplayed());
        AssertJUnit.assertTrue("Warehouse Instructions Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Warehouse Instructions Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Warehouse Instructions Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Warehouse Instructions Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Warehouse Instructions Page: New Warehouse Instruction button not displayed",newWhsInst.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement whsInst = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(whsInstField));
    }

    public void deleteWrhsInstructions(){
        int nrOfResults = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();

        if(nrOfResults > 1){
            System.out.println("Warehouse Instruction is already used");
            System.out.println("Deleting current Warehouse Instruction...");
            pressDelete(2);
            waitForElement();
        }
        System.out.println("Warehouse Instruction cleared");
    }

}
