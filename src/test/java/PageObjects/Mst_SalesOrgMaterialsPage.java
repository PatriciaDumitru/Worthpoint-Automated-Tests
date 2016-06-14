
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

public class Mst_SalesOrgMaterialsPage extends WBA_MasterDataPage {
    
    By articleField = By.id("filterArticle");
    By salesOrgField = By.cssSelector("#s2id_filterSalesOrgId > a");
    By plantField = By.id("s2id_filterPlantId");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By newSalesOrgMatButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");

    By brandField = By.id("s2id_filterBrandId");
    By ticketField = By.id("s2id_filterTicketId");
    By mumTypeField = By.id("s2id_filterMumTypeId");
    
    public Mst_SalesOrgMaterialsPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getArticleField() {
        return driver.findElement(articleField);
    }
    
    public WebElement getSalesOrgField() {
        return driver.findElement(salesOrgField);
    }
    
    public Mst_SalesOrgMaterialsPage setArticle(String item) {
        CommonTask.setInputField(driver, articleField, item);
        return this;
    }
    
    public Mst_SalesOrgMaterialsPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return this;
    }
    
    public Mst_SalesOrgMaterialsPage pressSearch() {
        getSearchButton().click();
        return new Mst_SalesOrgMaterialsPage(driver);
    }
    
    public Mst_SalesOrgMaterialsPage pressReset() {
        getResetButton().click();
        return new Mst_SalesOrgMaterialsPage(driver);
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
    
    public Mst_AddSalesOrgMaterialPage pressNewSalesOrgMat() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newSalesOrgMatButton));
        element.click();
        
        return new Mst_AddSalesOrgMaterialPage(driver);
    }
    
    public Mst_EditSalesOrgMaterialPage pressEdit(int row) {
        By editLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(editLocator));
        element.click();
        
        return new Mst_EditSalesOrgMaterialPage(driver);
    }
    
    public Mst_SalesOrgMaterialsPage pressDelete(int row) {
        By editLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(editLocator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_SalesOrgMaterialsPage(driver);
    }
    
    public int getRow(String salesOrg, String article) {
        if (!checkForRecords()) {
            return -1;
        }
        
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(2)");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(headerLocator));
        AssertJUnit.assertTrue("Sales Org Materials: Sales Org Column has moved, update locators",header.getText().equals("Sales Organisation"));
        
        By countField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(countField);
        int tableCount = (count >= 10) ? 10 : count;
        
        for (int i = 2; i < (tableCount+2); i++) {
            By salesOrgLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement salesOrgCell = driver.findElement(salesOrgLocator);
            if (salesOrgCell.getText().equals(salesOrg)) {
                
                By articleLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(4)");
                WebElement articleCell = driver.findElement(articleLocator);
                
                if (articleCell.getText().equals(article)) {
                    return i;
                }
                
            }
        }
        return -1;     
    }
    
    public String getHiddenTypes() {;
        
        By typeCell = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(2) > td:nth-child(7)");
        
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(typeCell));
        
        return driver.findElement(typeCell).getText();
        
    }
    
    public void checkFields() {
        //Check generic (search/reset) elements
        checkGenericFields();
        
        //Wait for all elements to be clickable
        WebElement article = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleField));
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Sales Org. Materials Master Page: Article Field not displayed correctly", getArticleField().isDisplayed());
        AssertJUnit.assertTrue("Sales Org. Materials Master Page: Sales Org Field not displayed correctly", getSalesOrgField().isDisplayed());
        
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(articleField));
    }

    public Mst_AddSalesOrgMaterialPage setBrand(String item) {
        CommonTask.setSearchField(driver, brandField, item);
        return new Mst_AddSalesOrgMaterialPage(driver);
    }

    public Mst_AddSalesOrgMaterialPage setPlant(String item) {
        CommonTask.setSearchField(driver, plantField, item);
        return new Mst_AddSalesOrgMaterialPage(driver);
    }

    public Mst_AddSalesOrgMaterialPage setTicket(String item) {
        CommonTask.setSearchField(driver, ticketField, item);
        return new Mst_AddSalesOrgMaterialPage(driver);
    }

    public Mst_AddSalesOrgMaterialPage setMUMType(String item) {
        CommonTask.setSearchField(driver, mumTypeField, item);
        return new Mst_AddSalesOrgMaterialPage(driver);
    }


    public void deleteSlsOrgMat(){

            int nrOfEntry = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();
            System.out.println(nrOfEntry - 1 +" Sales org material found with the name Test ");

            for(int i = nrOfEntry;i > 1; i--)
            {
                pressDelete(2);
                setSalesOrg("ID51");
                setPlant("ID10");
                setBrand("TEST");
                setTicket("000");
                setMUMType("Vicone");
                pressSearch();
                waitForElement();
            }
            System.out.println("Sales org material with Test criteria cleared");

    }

}
