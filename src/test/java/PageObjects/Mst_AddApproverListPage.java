
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

public class Mst_AddApproverListPage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("ApproverListSalesOrgId");
    By customerNameField = By.id("s2id_ApproverListCustomerId");
    By requesterField = By.id("ApproverListRequesterId");
    By valueStartField = By.id("ApproverListValueStart");
    By valueUntilField = By.id("ApproverListValueUntil");
    By emailNotificationField = By.id("ApproverListEnableEmailNotify");
    By saveButton = By.cssSelector("#ApproverListAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#ApproverListAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddApproverListPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement element = Wait.visible(driver,DataItems.breadcrumbLocator);
        return element;
    }
    
    public Mst_AddApproverListPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,salesOrgField,item);
        return new Mst_AddApproverListPage(driver);
    }
    
    public Mst_AddApproverListPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver,customerNameField,item);
        return new Mst_AddApproverListPage(driver);
    }
    
    public Mst_AddApproverListPage setRequester(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,requesterField,item);
        return new Mst_AddApproverListPage(driver);
    }
    
    public Mst_AddApproverListPage setValueStart(String item) throws InterruptedException {
        CommonTask.setInputField(driver,valueStartField,item);
        return new Mst_AddApproverListPage(driver);
    }
    
    public Mst_AddApproverListPage setValueUntil(String item) throws InterruptedException {
        CommonTask.setInputField(driver,valueUntilField,item);
        return new Mst_AddApproverListPage(driver);
    }
    
    public Mst_AddApproverListPage enableEmailNotif() {
        CommonTask.setCheckBox(driver,emailNotificationField);
        return new Mst_AddApproverListPage(driver);
    }
    
    public Mst_ApproverListPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        return new Mst_ApproverListPage(driver);
    }
    
    public Mst_ApproverListPage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();
        return new Mst_ApproverListPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement custName = Wait.clickable(driver,customerNameField);
        WebElement requester = Wait.clickable(driver,requesterField);
        WebElement valueStart = Wait.clickable(driver,valueStartField);
        WebElement valueUntil = Wait.clickable(driver,valueUntilField);
        WebElement emailNotif = Wait.clickable(driver,emailNotificationField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Approver List Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Approver List Page: Customer Name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Approver List Page: Requester field not displayed",requester.isDisplayed());
        AssertJUnit.assertTrue("Add Approver List Page: Value Start field not displayed",valueStart.isDisplayed());
        AssertJUnit.assertTrue("Add Approver List Page: Value Until field not displayed",valueUntil.isDisplayed());
        AssertJUnit.assertTrue("Add Approver List Page: E-mail notification field not displayed",emailNotif.isDisplayed());
        AssertJUnit.assertTrue("Add Approver List Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Approver List Page: Cancel button not displayed",cancel.isDisplayed());        
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,valueStartField);
    }
    
}
