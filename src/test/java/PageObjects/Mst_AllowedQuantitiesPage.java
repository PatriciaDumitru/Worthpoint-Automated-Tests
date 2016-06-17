
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_AllowedQuantitiesPage extends WBA_BasePage {
    
    By custNameField = By.id("s2id_filterCustomerId");
    By brandField = By.id("s2id_filterBrandId");
    By ticketField = By.id("s2id_filterTicketId");
    By mumTypeField = By.id("s2id_filterMumTypeId");
    By shadeField = By.id("s2id_filterShadeId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newAllowedQtyButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_AllowedQuantitiesPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AllowedQuantitiesPage setCustomerName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_AllowedQuantitiesPage(driver);
    }
    
    public Mst_AllowedQuantitiesPage setBrand(String item) {
        CommonTask.setSearchField(driver, brandField, item);
        return new Mst_AllowedQuantitiesPage(driver);
    }

    public Mst_AllowedQuantitiesPage setTicket(String item) {
        CommonTask.setSearchField(driver, ticketField, item);
        return new Mst_AllowedQuantitiesPage(driver);
    }

    public Mst_AllowedQuantitiesPage setMUMType(String item) {
        CommonTask.setSearchField(driver, mumTypeField, item);
        return new Mst_AllowedQuantitiesPage(driver);
    }
    
    public Mst_AllowedQuantitiesPage setShade(String item) {
        CommonTask.setSearchField(driver, shadeField, item);
        return new Mst_AllowedQuantitiesPage(driver);
    }
    
    public Mst_AllowedQuantitiesPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        return new Mst_AllowedQuantitiesPage(driver);
    }
    
    public Mst_AllowedQuantitiesPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        return new Mst_AllowedQuantitiesPage(driver);
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
        WebElement xlsxBtn = driver.findElement(xlsx);
        xlsxBtn.click();
        
        return new CCE_ExportDownloadPage(driver);
    }
    
    public Mst_EditAllowedQuantityPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditAllowedQuantityPage(driver);
    }
    
    public Mst_AllowedQuantitiesPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_AllowedQuantitiesPage(driver);
    }
    
    public Mst_AddAllowedQuantityPage pressNewAllowedQty() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(newAllowedQtyButton));
        element.click();
        
        return new Mst_AddAllowedQuantityPage(driver);
    }
    
    public int getRow(String brand, String qty) {
     
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(3) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        AssertJUnit.assertTrue("Allowed Quantities Page: Brand column has moved, update locators",header.getText().trim().equals("Brand"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count > 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            By locator1 = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(3)");
            WebElement cell1 = driver.findElement(locator1);
            
            if (cell1.getText().trim().equals(brand)) {
                By locator2 = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(7)");
                WebElement cell2 = driver.findElement(locator2);
                
                if (cell2.getText().trim().equals(qty)) {
                    return i;
                }
            }
            
        }
        return -1;
    }
    
    public void checkFields() {
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketField));
        WebElement mumType = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mumTypeField));
        WebElement shade = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement newAllowedQty = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newAllowedQtyButton));
    
        AssertJUnit.assertTrue("Allowed Quantities Page: Customer Name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Allowed Quantities Page: MUM Type field not displayed",mumType.isDisplayed());
        AssertJUnit.assertTrue("Allowed Quantities Page: Shade field not displayed",shade.isDisplayed());
        AssertJUnit.assertTrue("Allowed Quantities Page: Search field not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Allowed Quantities Page: Reset field not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Allowed Quantities Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Allowed Quantities Page: New Allowed Quantity button not displayed",newAllowedQty.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement newAllowedQty = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newAllowedQtyButton));
    }

    //Check if any previously Allowed Quantities were created and delete them before starting the test
    public void deleteAq(){
        int nrOfEntry = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();
        System.out.println(nrOfEntry - 1 +" Allowed quantities entries found");

        for(int i = nrOfEntry;i > 1; i--)
        {
            pressDelete(2);
            setCustomerName(DataItems.custDetails[0]);
            setBrand("ASTRA");
            setTicket("000");
            setMUMType("Cone");
            setShade("BLACK");
            pressSearch();
            waitForElement();
        }
        System.out.println("Allowed Quantity entries cleared");
    }
    
}
