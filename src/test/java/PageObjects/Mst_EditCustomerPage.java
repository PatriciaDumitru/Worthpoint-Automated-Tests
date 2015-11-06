
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditCustomerPage extends WBA_BasePage {
    
    //Locators
    By customerNameField = By.id("CustomerCustomerName");
    By approvalWorkflowBox = By.id("CustomerApprovalWorkflow");
    By saveButton = By.cssSelector("#CustomerEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    
    public Mst_EditCustomerPage(WebDriver driver) {
        super(driver);
    }
    
    public String getCustomerName() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerNameField));
        return field.getText();
    }
    
    public WebElement getApprovalWorkflowBox() {
        return driver.findElement(approvalWorkflowBox);
    }
    
    public Mst_EditCustomerPage setApprovalWorkflow() {
        CommonTask.setCheckBox(driver, approvalWorkflowBox);
        return new Mst_EditCustomerPage(driver);
    }
    
    public Mst_EditCustomerPage unsetApprovalWorkflow() {
        CommonTask.uncheckBox(driver, approvalWorkflowBox);
        return new Mst_EditCustomerPage(driver);
    }
    
    public Mst_CustomersPage pressSave() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        btn.click();
        
        return new Mst_CustomersPage(driver);
    }
    
    public void waitForElement() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerNameField));
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerNameField));
        WebElement approvalWkflw = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(approvalWorkflowBox));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Edit Customer Page: Customer name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Edit Customer Page: Approval Workflow Box not displayed",custName.isDisplayed());
        
    }
    
}
