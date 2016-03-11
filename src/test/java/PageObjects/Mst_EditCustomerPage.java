
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import java.util.List;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditCustomerPage extends WBA_BasePage {
    
    //Locators
    By customerNameField = By.id("CustomerCustomerName");
    By contractOrderField = By.id("CustomerOffOrder");
    By eCommShipNoticeField = By.id("Requester0ShipNoticeMail");
    By eCommShipNoticeLabel = By.cssSelector("#test > tbody > tr:nth-child(1) > th:nth-child(12)");
    By cceShipNoticeField = By.id("Requester0ShipNoticeMailCce");
    By cceShipNoticeLabel = By.cssSelector("#test > tbody > tr:nth-child(1) > th:nth-child(13)");
    By approvalWorkflowBox = By.id("CustomerApprovalWorkflow");
    By subAcctField = By.id("CustomerPayerEnabled");
    By subAcctLabel = By.xpath("/html/body/div[1]/div[3]/form/div[2]/table/tbody/tr[35]/td[1]/label");

    By userTypeField = By.id("Requester0UserTypeId");
    By saveButton = By.cssSelector("#CustomerEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    
    public Mst_EditCustomerPage(WebDriver driver) {
        super(driver);
    }
    
    public String getCustomerName() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerNameField));
        return field.getAttribute("value");
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public WebElement getApprovalWorkflowBox() {
        return driver.findElement(approvalWorkflowBox);
    }
    
    public WebElement getContractOrderField() {
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(contractOrderField));
        return field;
    }
    
    public WebElement getSubAcctField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(subAcctField));
        return element;
    }
    
    public WebElement getSubAcctLabel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(subAcctLabel));
        return element;
    }
    
    public WebElement getEcommMailNotificationField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(eCommShipNoticeField));
        return element;
    }
    
    public WebElement getEcommMailNotificationLabel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(eCommShipNoticeLabel));
        return element;
    }
    
    public WebElement getCCEMailNotificationField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cceShipNoticeField));
        return element;
    }
    
    public WebElement getCCEMailNotificationLabel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(cceShipNoticeLabel));
        return element;
    }
    
    public Mst_EditCustomerPage setApprovalWorkflow() {
        CommonTask.setCheckBox(driver, approvalWorkflowBox);
        return new Mst_EditCustomerPage(driver);
    }
    
    public Mst_EditCustomerPage unsetApprovalWorkflow() {
        CommonTask.uncheckBox(driver, approvalWorkflowBox);
        return new Mst_EditCustomerPage(driver);
    }
    
    public Mst_EditCustomerPage setCallOffOrder() {
        CommonTask.setCheckBox(driver,contractOrderField);
        return new Mst_EditCustomerPage(driver);
    }
    
    public Mst_EditCustomerPage unsetCallOffOrder() {
        CommonTask.uncheckBox(driver, contractOrderField);
        return new Mst_EditCustomerPage(driver);
    }
    
    public boolean findUserType(String type) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(userTypeField));
        element.click();
        
        Select select = new Select(element);
        List<WebElement> elements = select.getOptions();
        
        for (WebElement e : elements) {
            if (e.getText().equals(type)) {
                return true;
            }
        }
        
        return false;
    }
    
    public Mst_CustomersPage pressSave() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        btn.click();
        
        return new Mst_CustomersPage(driver);
    }
    
    public Mst_EditCustomerPage setCustomerName(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerNameField));
        element.clear();
        
        CommonTask.setInputField(driver, customerNameField, item);
        return new Mst_EditCustomerPage(driver);
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

    public int getRow(String title) {

        if (!checkForRecords()) {
            return -1;
        }
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(2) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        AssertJUnit.assertTrue("Customer Private Articles Page: Customer name column has moved, update locators",header.getText().equals("Customer Name"));

        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count > 10) ? 10 : count;

        for (int i = 2; i < (tableCount+2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (cell.getText().equals(title)) {
                return i;
            }
        }
        return -1;
    }
    
}
