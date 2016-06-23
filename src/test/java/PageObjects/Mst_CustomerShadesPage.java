
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

public class Mst_CustomerShadesPage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("s2id_filterSalesOrgId");
    By custNameField = By.id("s2id_filterCustomerId");
    By coatsShadeField = By.id("s2id_filterShadeId");
    By statusField = By.id("s2id_filterStatusId");
    By lastUpFromField = By.id("filterUpdatedFrom");
    By lastUpToField = By.id("filterUpdatedTo");
    By custShadeField = By.id("filterCustomerShadeName");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newCustShadeButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_CustomerShadesPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_CustomerShadesPage setCustomerName(String item) {
        CommonTask.setSearchField(driver,custNameField,item);
        return new Mst_CustomerShadesPage(driver);
    }
    
    public Mst_CustomerShadesPage setCoatsShade(String item) {
        CommonTask.setSearchField(driver,coatsShadeField,item);
        return new Mst_CustomerShadesPage(driver);
    }
    
    public Mst_CustomerShadesPage setCustomerShade(String item) {
        CommonTask.setInputField(driver,custShadeField,item);
        return new Mst_CustomerShadesPage(driver);
    }
    
    public Mst_CustomerShadesPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        
        return new Mst_CustomerShadesPage(driver);
    }
    
    public Mst_CustomerShadesPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        
        return new Mst_CustomerShadesPage(driver);
    }
    
    public Mst_ImportPage pressImport() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        element.click();
        
        return new Mst_ImportPage(driver);
    }
    
    public Mst_AddCustShadePage pressNewCustomerShade() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newCustShadeButton));
        element.click();
        
        return new Mst_AddCustShadePage(driver);
    }
    
    public Mst_EditCustShadePage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditCustShadePage(driver);
    }
    
    public Mst_CustomerShadesPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_CustomerShadesPage(driver);
    }
    
    public CCE_ExportDownloadPage pressExport() {
        WebElement exportBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        
        Actions action = new Actions(driver);
        action.moveToElement(exportBtn).build().perform();
        
        By xlsx = By.partialLinkText("XLSX");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(xlsx));
        element.click();
        
        return new CCE_ExportDownloadPage(driver);
    }

    //delete if not used
    public boolean itemPresent(){
        By shadeHeader = By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/th[4]/a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeHeader));
        int nrOfEntry = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();

        if(nrOfEntry>1){
            return true;
        }
        return false;
    }

    public int getRow(String shadeName) {
        
        By shadeHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(4) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeHeader));
        
        AssertJUnit.assertTrue("Customer Shades Page: Customer Shade column has moved, update locators",header.getText().equals("Customer Shade"));
        
        By recordField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int recordCount = this.getRecordCount(recordField);
        
        for (int i = 2; i < (recordCount+2); i++) {
            
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(4)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            String text = cell.getText().trim();
            
            if (text.equals(shadeName)) {
                return i;
            } 
        }
        
        return -1;
    }

    public int getNrOfEntry() {
        By brandHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(4) > a");
        WebElement header = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(brandHeader));

        AssertJUnit.assertTrue("Customer Brands Page: Customer Brand column has moved, update locators", header.getText().equals("Customer Shade"));

        int nrOfEntry = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();

        if (nrOfEntry > 1){
            return 1;
        }
        return -1;
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement coatsShade = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsShadeField));
        WebElement custShade = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custShadeField));
        WebElement statusFld = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(statusField));
        WebElement lastUpdFrom = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lastUpFromField));
        WebElement lastUpdTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lastUpToField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newShade = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newCustShadeButton));
               
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Customer Shades Page: Sales Organisation field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Customer Shades Page: Customer Name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Customer Shades Page: Coats Shade field not displayed",coatsShade.isDisplayed());
        AssertJUnit.assertTrue("Customer Shades Page: Customer Shade field not displayed",custShade.isDisplayed());
        AssertJUnit.assertTrue("Customer Shades Page: Status field not displayed",statusFld.isDisplayed());
        AssertJUnit.assertTrue("Customer Shades Page: Last Updated from field not displayed",lastUpdFrom.isDisplayed());
        AssertJUnit.assertTrue("Customer Shades Page: last Update Tofield not displayed",lastUpdTo.isDisplayed());
        AssertJUnit.assertTrue("Customer Shades Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Customer Shades Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Customer Shades Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Customer Shades Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Customer Shades Page: New Customer Shade button not displayed",newShade.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement custShade = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custShadeField));
    }

    public Mst_CustomerShadesPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return new Mst_CustomerShadesPage(driver);
    }

    public void deleteCustShade(){
        int nrOfEntry = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();
        System.out.println(nrOfEntry - 1 +" Customer Shade matching the test criteria found ");

        for(int i = nrOfEntry;i > 1; i--)
        {
            pressDelete(2);
            setSalesOrg(DataItems.salesOrganisation);
            setCustomerName(DataItems.custDetails[0]);
            pressSearch();
            waitForElement();
        }
        System.out.println("Customer Shade cleared");
    }
}
