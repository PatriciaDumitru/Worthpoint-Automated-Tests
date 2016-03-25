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

public class Ecomm_WaitingForShadePage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("s2id_filterBulkOrderSalesOrgId");
    By custNameField = By.id("s2id_filterBulkOrderCustomerId");
    By createDateFromField = By.id("filterBulkOrderCreatedFrom");
    By createDateToField = By.id("filterBulkOrderCreatedTo");
    By custPOField = By.id("filterBulkOrderPoNumber");
    By orderNoField = By.id("s2id_filterBulkOrderId");
    By searchButton = By.cssSelector("#FilterWaitingforshadesListForm > div.grid_12 > table > tbody > tr:nth-child(4) > td > div > input");
    By resetButton = By.cssSelector("#FilterWaitingforshadesListForm > div.grid_12 > table > tbody > tr:nth-child(4) > td > a");
    By editButton = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(1) > a > span");
    By viewButton = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2) > a > span");
    //table content locators
    By tableSalesOrg = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr > td:nth-child(3)");
    By tableCustName = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr > td:nth-child(4)");
    By tableCustPONo = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr > td:nth-child(5)");
    By tableOrderNo = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr > td:nth-child(6)");
    By tableNoOfOrderLines = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr > td:nth-child(7)");

    public Ecomm_WaitingForShadePage(WebDriver driver) {
        super(driver);
    }
    
    public Ecomm_WaitingForShadePage setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return this;
    }
    
    public Ecomm_WaitingForShadePage setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return this;
    }
    
    public Ecomm_WaitingForShadePage setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        return this;
    }
    
    public Ecomm_WaitingForShadePage setCustPO(String item) {
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
    
    public Ecomm_WaitingForShadePage pressSearch() {
        WebElement search = Wait.clickable(driver,searchButton);
        search.click();
        return this;
    }
    
    public Ecomm_WaitingForShadePage pressReset() {
        WebElement reset = Wait.clickable(driver,resetButton);
        reset.click();
        return this;
    }
    
    public Ecomm_ShadeOrderConfirmationPage pressEdit() {
        WebElement edit = Wait.clickable(driver,editButton);
        edit.click();
        return new Ecomm_ShadeOrderConfirmationPage(driver);
    }
    
    public Ecomm_OrderViewPage pressView() {
        WebElement view = Wait.clickable(driver,viewButton);
        view.click();
        return new Ecomm_OrderViewPage(driver);
    }

    public void waitForElement() {
        WebElement waitForSearchBtn = Wait.clickable(driver,searchButton);
    }
    
}
