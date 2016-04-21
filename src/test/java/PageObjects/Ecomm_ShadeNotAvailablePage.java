
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ecomm_ShadeNotAvailablePage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("s2id_filterBulkOrderSalesOrgId");
    By custNameField = By.id("s2id_filterBulkOrderCustomerId");
    By createDateFromField = By.id("filterBulkOrderCreatedFrom");
    By createDateToField = By.id("filterBulkOrderCreatedTo");
    By custPOField = By.id("filterBulkOrderPoNumber");
    By orderNoField = By.id("s2id_filterBulkOrderId");
    By searchButton = By.cssSelector("#FilterShadenotavailableListForm > div.grid_12 > table > tbody > tr:nth-child(4) > td > div > input");
    By resetButton = By.cssSelector("#FilterShadenotavailableListForm > div.grid_12 > table > tbody > tr:nth-child(4) > td > a");
    By editButton = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(1)");
    By viewButton = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2) > a");
    //table content locators
    By tableSalesOrg = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr > td:nth-child(3)");
    By tableCustName = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr > td:nth-child(4)");
    By tableCustPONo = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr > td:nth-child(5)");
    By tableOrderNo = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr > td:nth-child(6)");
    By tableNoOfOrderLines = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr > td:nth-child(7)");
    
    public Ecomm_ShadeNotAvailablePage(WebDriver driver) {
        super(driver);
    }
    
    public Ecomm_ShadeNotAvailablePage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return this;
    }
    
    public Ecomm_ShadeNotAvailablePage setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return this;
    }
    
    public Ecomm_ShadeNotAvailablePage setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        return this;
    }
    
    public Ecomm_ShadeNotAvailablePage setCustPO(String item) {
        CommonTask.setInputField(driver, custPOField, item);
        return this;
    }

    public String getTableSalesOrgText() {
        String text = driver.findElement(tableSalesOrg).getText();
        return text;
    }

    public String getTableCustNameText() {
        String text = driver.findElement(tableCustName).getText();
        return text;
    }

    public String getTableCustPONo() {
        String text = driver.findElement(tableCustPONo).getText();
        return text;
    }

    public String getTableOrderNo() {
        String text = driver.findElement(tableOrderNo).getText();
        return text;
    }

    public String getTableNoOfOrderLines() {
        String text = driver.findElement(tableNoOfOrderLines).getText();
        return text;
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement createFrom = Wait.clickable(driver,createDateFromField);
        WebElement createTo = Wait.clickable(driver,createDateToField);
        WebElement custPO = Wait.clickable(driver,custPOField);
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement searchBtn = Wait.clickable(driver,searchButton);
        WebElement resetBtn = Wait.clickable(driver,resetButton);
        WebElement editBtn = Wait.clickable(driver,editButton);
        WebElement viewBtn = Wait.clickable(driver,viewButton);
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Shade Not Available Page: Sales Org Field not displayed correctly",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: Customer Name Field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: Creation Date From Field not displayed correctly",createFrom.isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: Creation Date To Field not displayed correctly",createTo.isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: Customer PO Field not displayed correctly",custPO.isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: Order No Field not displayed correctly",orderNo.isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: Search button not displayed correctly",searchBtn.isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: Reset button not displayed correctly",resetBtn.isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: Edit button not displayed correctly",editBtn.isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Page: View button not displayed correctly",viewBtn.isDisplayed());
    }
    
    public Ecomm_ShadeNotAvailablePage pressSearch() {
        WebElement search = Wait.clickable(driver,searchButton);
        search.click();
        return this;
    }
    
    public Ecomm_ShadeNotAvailablePage pressReset() {
        WebElement reset = Wait.clickable(driver,resetButton);
        reset.click();
        return this;
    }

    public Ecomm_ShadeOrderConfirmationPage pressEdit(int row) {
        By editBtn = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(1) > a > span");
        WebElement edit = Wait.clickable(driver,editBtn);
        edit.click();
        return new Ecomm_ShadeOrderConfirmationPage(driver);
    }

    public Ecomm_ShadeOrderConfirmationPage pressEdit2() {
        WebElement edit = Wait.clickable(driver,editButton);
        edit.click();
        return new Ecomm_ShadeOrderConfirmationPage(driver);
    }
    
    public Ecomm_OrderViewPage pressView(int row) {
        By viewBtn = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+row+") > td:nth-child(2) > a > span");
        WebElement view = Wait.clickable(driver,viewBtn);
        view.click();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public int getSingleLineRecord() {
        
        By orderLinesHeader = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > thead > tr:nth-child(1) > th:nth-child(6) > label");
        WebElement header = Wait.visible(driver, orderLinesHeader);
        AssertJUnit.assertTrue("Shade Not Available Page: Order Lines column has moved, update locators",header.getText().trim().equals("No. of Order Lines"));
        
        By recordsField = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > div > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count>10) ? 10 : count;
        
        for (int i = 1; i <= tableCount; i++) {
            By locator = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+i+") > td:nth-child(7)");
            WebElement cell = Wait.visible(driver, locator);
            
            if (cell.getText().equals("1")) {
                return i;
            }
        }
        return -1;
    }
    
    
}
