
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import static PageObjects.Ecomm_ManualEntryPage.customerNameField;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_CountriesPage extends WBA_BasePage {
    
    //Locators
    By countryNameField = By.id("filterCountryName");
    By countryCodeField = By.id("filterCountryCode");
    By languageField = By.id("s2id_filterLanguageId");
    By timezoneField = By.id("s2id_filterTimezoneId");
    By statusField = By.id("s2id_filterStatusId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newCountryButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_CountriesPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return driver.findElement(DataItems.breadcrumbLocator);
    }
    
    public WebElement getCountryNameField() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(countryNameField));
        return driver.findElement(countryNameField);
    }
    
    public WebElement getCountryCodeField() {
        return driver.findElement(countryCodeField);
    }
    
    public WebElement getLanguageField() {
        return driver.findElement(languageField);
    }
    
    public WebElement getTimezoneField() {
        return driver.findElement(timezoneField);
    }
    
    public WebElement getStatusField() {
        return driver.findElement(statusField);
    }
    
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public WebElement getImportButton() {
        return driver.findElement(importButton);
    }
    
    public WebElement getExportButton() {
        return driver.findElement(exportButton);
    }
    
    public WebElement getNewCountryButton() {
        return driver.findElement(newCountryButton);
    }
    
    public Mst_CountriesPage setCountryName(String item) {
        CommonTask.setInputField(driver,countryNameField,item);
        return new Mst_CountriesPage(driver);
    }
    
    public Mst_CountriesPage setCountryCode(String item) {
        CommonTask.setInputField(driver,countryCodeField,item);
        return new Mst_CountriesPage(driver);
    }
    
    public Mst_CountriesPage pressSearch() {
        WebElement button = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        button.click();
        
        return new Mst_CountriesPage(driver);
    }
    
    public Mst_CountriesPage pressReset() {
        WebElement button = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        button.click();
        
        return new Mst_CountriesPage(driver);
    }
    
    public Mst_ImportPage pressImport() {
        WebElement button = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        button.click();
        
        return new Mst_ImportPage(driver);
    }
    
    public Mst_AddCountryPage pressNewCountry() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newCountryButton));
        element.click();
        
        return new Mst_AddCountryPage(driver);
    }
    
    public CCE_ExportDownloadPage pressExport() {
        WebElement button = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        Actions action = new Actions(driver);
        action.moveToElement(button).build().perform();
        
        By typeLocator = By.partialLinkText("XLSX");
        WebElement type = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(typeLocator));
        type.click();
        
        return new CCE_ExportDownloadPage(driver);
    }
    
    public Mst_EditCountryPage pressEdit(int row) {
        //By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        By locator = By.xpath("/html/body/div[1]/div[3]/div[2]/table/tbody/tr["+row+"]/td[9]/a[1]/span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        
        element.click();
        
        return new Mst_EditCountryPage(driver);
    }
    
    public Mst_EditCountryPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_EditCountryPage(driver);
    }
    
    public int getRow(String code) {
        By countryCodeHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(3) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(countryCodeHeader));
        AssertJUnit.assertTrue("Countries Page: Country Code column has moved, update locators",header.getText().equals("Country Code"));
        
        if (!checkForRecords()) {
            return -1;
        }
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = this.getRecordCount(recordsField);
        
        for (int i = 2; i < (count + 2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(3)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
            if (cell.getText().trim().equals(code)) {
                return i;
            }
        }
        
        return -1;  
    }
    
    public int getRowLanguage(String lan) {
        By langHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(4)");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(langHeader));
        AssertJUnit.assertTrue("Countries Page: Country Code column has moved, update locators",header.getText().equals("Language"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = this.getRecordCount(recordsField);
        
        for (int i = 2; i < (count + 2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(4)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
            if (cell.getText().trim().equals(lan)) {
                return i;
            }
        }
        
        return -1;  
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(countryCodeField));
    }
    
    public void checkFields() {
        //Wait for clickable
        WebElement countryName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(countryNameField));
        WebElement countryCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(countryCodeField));
        WebElement language = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(languageField));
        WebElement timezone = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(timezoneField));
        WebElement status = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(statusField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement impo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newButton = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newCountryButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Countries Page: Country Name field not displayed as expected",getCountryNameField().isDisplayed());
        AssertJUnit.assertTrue("Countries Page: Country Code field not displayed as expected",getCountryCodeField().isDisplayed());
        AssertJUnit.assertTrue("Countries Page: Language field not displayed as expected",getLanguageField().isDisplayed());
        AssertJUnit.assertTrue("Countries Page: Timezone field not displayed as expected",getTimezoneField().isDisplayed());
        AssertJUnit.assertTrue("Countries Page: Status field not displayed as expected",getStatusField().isDisplayed());
        AssertJUnit.assertTrue("Countries Page: Search button not displayed as expected",getSearchButton().isDisplayed());
        AssertJUnit.assertTrue("Countries Page: Reset button not displayed as expected",getResetButton().isDisplayed());
        AssertJUnit.assertTrue("Countries Page: Import button not displayed as expected",getImportButton().isDisplayed());
        AssertJUnit.assertTrue("Countries Page: Export button not displayed as expected",getExportButton().isDisplayed());
        AssertJUnit.assertTrue("Countries Page: New Country button not displayed as expected",getNewCountryButton().isDisplayed());
    }
    
}
