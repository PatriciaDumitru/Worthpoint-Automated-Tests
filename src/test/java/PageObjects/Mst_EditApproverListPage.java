
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;


public class Mst_EditApproverListPage extends WBA_BasePage{
    
    //Locators
    By salesOrgField = By.id("ApproverListSalesOrgId");
    By customerNameField = By.id("s2id_ApproverListCustomerId");
    By requesterField = By.id("ApproverListRequesterId");
    By valueStartField = By.id("ApproverListValueStart");
    By valueUntilField = By.id("ApproverListValueUntil");
    By emailNotificationField = By.id("ApproverListEnableEmailNotify");
    By saveButton = By.cssSelector("#ApproverListEditForm > div.actions > ul > li:nth-child(1)");
    By cancelButton = By.cssSelector("#ApproverListEditForm > div.actions > ul > li:nth-child(2)");
    
    public Mst_EditApproverListPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
        return element;
    }
    
    public Mst_EditApproverListPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,salesOrgField,item);
        return new Mst_EditApproverListPage(driver);
    }
    
    public Mst_EditApproverListPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver,customerNameField,item);
        return new Mst_EditApproverListPage(driver);
    }
    
    public Mst_EditApproverListPage setRequester(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,requesterField,item);
        return new Mst_EditApproverListPage(driver);
    }
    
    public Mst_EditApproverListPage setValueStart(String item) throws InterruptedException {
        CommonTask.setInputField(driver,valueStartField,item);
        return new Mst_EditApproverListPage(driver);
    }
    
    public Mst_EditApproverListPage setValueUntil(String item) throws InterruptedException {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(valueUntilField));
        element.clear();
        CommonTask.setInputField(driver,valueUntilField,item);
        return new Mst_EditApproverListPage(driver);
    }
    
    public String getRequester() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requesterField));
        Select select = new Select(field);
        return select.getFirstSelectedOption().getText().trim();
    }
    
    public Mst_EditApproverListPage enableEmailNotif() {
        CommonTask.setCheckBox(driver,emailNotificationField);
        return new Mst_EditApproverListPage(driver);
    }
    
    public Mst_ApproverListPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        return new Mst_ApproverListPage(driver);
    }
    
    public Mst_ApproverListPage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        return new Mst_ApproverListPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerNameField));
        WebElement requester = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement valueStart = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(valueStartField));
        WebElement valueUntil = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(valueUntilField));
        WebElement emailNotif = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(emailNotificationField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Approver List Page: Sales Org field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Approver List Page: Customer Name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Approver List Page: Requester field not displayed",requester.isDisplayed());
        AssertJUnit.assertTrue("Add Approver List Page: Value Start field not displayed",valueStart.isDisplayed());
        AssertJUnit.assertTrue("Add Approver List Page: Value Until field not displayed",valueUntil.isDisplayed());
        AssertJUnit.assertTrue("Add Approver List Page: E-mail notification field not displayed",emailNotif.isDisplayed());
        AssertJUnit.assertTrue("Add Approver List Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Approver List Page: Cancel button not displayed",save.isDisplayed());        
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(valueStartField));
    }
    
}
