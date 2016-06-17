
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

public class Mst_ShipToPartiesPage extends WBA_BasePage {
    
    By countryCodeField = By.id("s2id_filterCountryId");
    By salesOrgField = By.id("s2id_filterSalesOrgId");
    By custNameField = By.id("filterCustomerCustomerName");
    By shipToPartyNoField = By.id("filterShipToPartyNo");
    By shipToPartyNameField = By.id("filterShipToPartyName");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newShipToPartyButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");

    By partyNumberField = By.id("filterShipToPartyNo");
    
    public Mst_ShipToPartiesPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_ShipToPartiesPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return new Mst_ShipToPartiesPage(driver);
    }
    
    public Mst_ShipToPartiesPage setCustomerName(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        element.clear();
        
        CommonTask.setInputField(driver, custNameField, item);
        return new Mst_ShipToPartiesPage(driver);
    }

    public Mst_AddShipToPartyPage setPartyNumber(String item) {
        CommonTask.setInputField(driver,partyNumberField,item);
        return new Mst_AddShipToPartyPage(driver);
    }
    
    public Mst_ShipToPartiesPage setShipToPartyName(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        element.clear();
        
        CommonTask.setInputField(driver, shipToPartyNameField, item);
        return new Mst_ShipToPartiesPage(driver);
    }
    
    public Mst_ShipToPartiesPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_ShipToPartiesPage(driver);
    }
    
    public Mst_ShipToPartiesPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_ShipToPartiesPage(driver);
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
    
    public Mst_AddShipToPartyPage pressNewShipToParty() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newShipToPartyButton));
        element.click();
        
        return new Mst_AddShipToPartyPage(driver);
    }
    
    public int getRow(String shipToName) {
        if (!checkForRecords()) {
            return -1;
        }
        
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(5) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        AssertJUnit.assertTrue("Ship To Parties Page: Ship To Party name column has moved, update locators",header.getText().equals("Ship to Party Name"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count > 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(5)");
            WebElement cell = driver.findElement(locator);
            if (cell.getText().trim().equals(shipToName)) {
                return i;
            }
        }
        return -1;
    }
    
    public Mst_EditShipToPartyPage pressEdit(int row) {
        By editButton = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(editButton));
        element.click();
        
        return new Mst_EditShipToPartyPage(driver);
    }
    
    public Mst_EditShipToPartyPage pressDelete(int row) {
        By button = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(button));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_EditShipToPartyPage(driver);
    }
    
    public void checkFields() {
        WebElement countryCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(countryCodeField));
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement shipToPartyNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToPartyNoField));
        WebElement shipToPartyName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToPartyNameField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newShipToParty = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newShipToPartyButton));
        
        AssertJUnit.assertTrue("Ship To Parties Page: Country Code field not displayed",countryCode.isDisplayed());
        AssertJUnit.assertTrue("Ship To Parties Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Ship To Parties Page: Customer Name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Ship To Parties Page: Ship to Party No field not displayed",shipToPartyNo.isDisplayed());
        AssertJUnit.assertTrue("Ship To Parties Page: Ship to Party Name field not displayed",shipToPartyName.isDisplayed());
        AssertJUnit.assertTrue("Ship To Parties Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Ship To Parties Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Ship To Parties Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Ship To Parties Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Ship To Parties Page: New Ship To Party button not displayed",newShipToParty.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement shipToPartyName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToPartyNameField));
    }

    public void deleteShipToParty(){
        int nrOfEntry = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();
        System.out.println(nrOfEntry - 1 +" Ship to Parties matching the test criteria found ");

        for(int i = nrOfEntry;i > 1; i--)
        {
            pressDelete(2);
            setCustomerName(DataItems.custDetails[0]);
            setPartyNumber("AUT01");
            setSalesOrg("ID51");
            pressSearch();
            waitForElement();
        }
        System.out.println("Ship to Parties deleted");
    }

}
