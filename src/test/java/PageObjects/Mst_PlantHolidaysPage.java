
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

public class Mst_PlantHolidaysPage extends WBA_BasePage {
    
    //Locators
    By plantNameField = By.id("s2id_filterPlantId");
    By lastUpdatedFromField = By.id("filterUpdatedFrom");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newPlantButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    
    public Mst_PlantHolidaysPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(DataItems.breadcrumbLocator));
    }
    
    public Mst_PlantHolidaysPage setPlantName(String item) {
        CommonTask.setSearchField(driver,plantNameField,item);
        return new Mst_PlantHolidaysPage(driver);
    }
    
    public Mst_PlantHolidaysPage setLastUpdatedFrom(String item) {
        CommonTask.setInputField(driver,lastUpdatedFromField,item);
        return new Mst_PlantHolidaysPage(driver);
    }
    
    public Mst_PlantHolidaysPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        return new Mst_PlantHolidaysPage(driver);
    }
    
    public Mst_PlantHolidaysPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        return new Mst_PlantHolidaysPage(driver);
    }
    
    public Mst_ImportPage pressImport() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        element.click();
        
        return new Mst_ImportPage(driver);
    }
    
    public CCE_ExportDownloadPage pressExport() {
        WebElement exportBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        Actions action = new Actions(driver);
        action.moveToElement(exportBtn).build().perform();
        
        By xlsx = By.partialLinkText("XLSX");
        WebElement xlsxBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(xlsx));
        xlsxBtn.click();
        
        return new CCE_ExportDownloadPage(driver);
    }
    
    public Mst_EditPlantHolidayPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditPlantHolidayPage(driver);
    }
    
    public Mst_PlantHolidaysPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_PlantHolidaysPage(driver);
    }
    
    public Mst_AddPlantHolidayPage pressNewPlantHoliday() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newPlantButton));
        element.click();
        
        return new Mst_AddPlantHolidayPage(driver);
    }
    
    public int getRow(String holidayDesc) {
        if (!checkForRecords()) {
            return -1;
        }
        
        By descHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(5) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(descHeader));
        
        AssertJUnit.assertTrue("Plant Holidays Page: Holiday Description column has moved, update locators",header.getText().equals("Holiday Description"));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        
        int tableCount = (count >= 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(5)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
            
            if (cell.getText().equals(holidayDesc)) {
                return i;
            }
            
        }
        
        return -1;
    }
    
    public void checkFields() {
        WebElement plantName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantNameField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importB = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newPlant = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newPlantButton));
        
        AssertJUnit.assertTrue("Plant Holidays Page: Plant Name field not displayed",plantName.isDisplayed());
        AssertJUnit.assertTrue("Plant Holidays Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Plant Holidays Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Plant Holidays Page: Import button not displayed",importB.isDisplayed());
        AssertJUnit.assertTrue("Plant Holidays Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Plant Holidays Page: New Plant button not displayed",newPlant.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement plantName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantNameField));
    }

    public void deleteHolyDay(){
        int nrOfResults = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();

        if(nrOfResults > 1){
            System.out.println("HolyDay is already used");
            System.out.println("Deleting current HolyDay");
            pressDelete(2);
            waitForElement();
        }
        System.out.println("HolyDay cleared");
    }

}
