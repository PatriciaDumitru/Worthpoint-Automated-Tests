
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditSubAccountPage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("PayerSalesOrgId");
    By customerNameField = By.id("s2id_PayerCustomerId");
    By subAccountNoField = By.id("PayerPayerNo");
    By subAccountNameField = By.id("PayerPayerName");
    By commentField = By.id("PayerComment");
    By saveButton = By.cssSelector("#PayerEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#PayerEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditSubAccountPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
        return element;
    }
    
    public Mst_SubAccountPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_SubAccountPage(driver);
    }
    
    public Mst_SubAccountPage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        
        return new Mst_SubAccountPage(driver);
    }
    
    public Mst_SubAccountPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,salesOrgField,item);
        return new Mst_SubAccountPage(driver);
    }
    
    public Mst_SubAccountPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver,customerNameField,item);
        return new Mst_SubAccountPage(driver);
    }
    
    public Mst_SubAccountPage setSubAccountNumber(String item) throws InterruptedException {
        CommonTask.setInputField(driver,subAccountNoField,item);
        return new Mst_SubAccountPage(driver);
    }
    
    public Mst_SubAccountPage setSubAccountName(String item) throws InterruptedException {
        CommonTask.setInputField(driver,subAccountNameField,item);
        return new Mst_SubAccountPage(driver);
    }
    
    public Mst_SubAccountPage setComment(String item) throws InterruptedException {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(commentField));
        element.clear();
        
        CommonTask.setInputField(driver,commentField,item);
        return new Mst_SubAccountPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerNameField));
        WebElement subNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(subAccountNoField));
        WebElement subName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(subAccountNameField));
        WebElement comment = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(commentField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Sub Account Page: Sales Organisation field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Sub Account Page: Customer Name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Sub Account Page: Sub Account Number field not displayed",subNo.isDisplayed());
        AssertJUnit.assertTrue("Add Sub Account Page: Sub Account Name field not displayed",subName.isDisplayed());
        AssertJUnit.assertTrue("Add Sub Account Page: Comment field not displayed",comment.isDisplayed());
        AssertJUnit.assertTrue("Add Sub Account Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Sub Account Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(commentField));
    }
    
}
