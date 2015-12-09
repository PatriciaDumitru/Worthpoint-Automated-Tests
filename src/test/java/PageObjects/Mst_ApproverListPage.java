
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

public class Mst_ApproverListPage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("s2id_filterSalesOrgId");
    By custNameField = By.id("s2id_filterCustomerId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1)");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2)");
    By exportButton = By.cssSelector("#export-menu > a");
    By newListButton = By.cssSelector("#content > div.actions > ul > li:nth-child(2)");
    
    public Mst_ApproverListPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
        return element;
    }
    
    public WebElement getSalesOrgField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(salesOrgField));
        return element;
    }
    
    public WebElement getCustNameField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(custNameField));
        return element;
    }
    
    public WebElement getSearchButton() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        return element;
    }
    
    public WebElement getResetButton() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(resetButton));
        return element;
    }
    
    public WebElement getExportButton() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(exportButton));
        return element;
    }
    
    public WebElement getNewListButton() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(newListButton));
        return element;
    }
    
    public Mst_ApproverListPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return new Mst_ApproverListPage(driver);
    }
    
    public Mst_ApproverListPage setCustomerName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return new Mst_ApproverListPage(driver);
    }
    
    public Mst_ApproverListPage pressSearch() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();
        
        return new Mst_ApproverListPage(driver);
    }
    
    public Mst_ApproverListPage pressReset() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        btn.click();
        
        return new Mst_ApproverListPage(driver);
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
    
    public Mst_AddApproverListPage pressAddApproverList() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newListButton));
        btn.click();
        
        return new Mst_AddApproverListPage(driver);
    }
    
    public Mst_EditApproverListPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1)");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        
        element.click();
        return new Mst_EditApproverListPage(driver);
    }
    
    public Mst_ApproverListPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_ApproverListPage(driver);
    }
    
    public int getRow(String username) {
        By usernameHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(4) > a");
        WebElement head = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(usernameHeader));
        
        AssertJUnit.assertTrue("Approver List Page: Username column has moved. Update locators",head.getText().equals("Username"));
        
        By countField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int recCount = this.getRecordCount(countField);
        
        for (int i = 2; i < (recCount + 2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(4)");
            String text = driver.findElement(locator).getText().trim();
            if (text.equals(username)) {
                return i;
            }
        }
        
        return -1;
        
    }
    
    public String getValueUntil(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(6)");
        WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return cell.getText().trim();
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newList = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newListButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Approver List Page: Sales Org Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Approver List Page: Customer Name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Approver List Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Approver List Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Approver List Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Approver List Page: New Approver List button not displayed",newList.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
    }
    
}
