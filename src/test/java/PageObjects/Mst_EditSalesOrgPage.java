
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditSalesOrgPage extends WBA_BasePage {
    
    //Locators
    By nameField = By.id("SalesOrgSalesOrgName");
    By descriptionField = By.id("SalesOrgDescription");
    By contractOrderField = By.id("SalesOrgOffOrder");
    By subAcctField = By.id("SalesOrgIsPayerEnabled");
    By subAcctLabel = By.cssSelector("#SalesOrgEditForm > div.frm > table > tbody > tr:nth-child(5) > td:nth-child(1) > label");
    By mailNotificationField = By.id("SalesOrgIsCceshipnoticeEnabled");
    By mailNotificationLabel = By.cssSelector("#SalesOrgEditForm > div.frm > table > tbody > tr:nth-child(6) > td:nth-child(1) > label");
    By saveButton = By.id("save");
    By cancelButton = By.cssSelector("#SalesOrgEditForm > div.actions > ul > li:nth-child(2) > a");
    By orderUploadCheckBox=By.id("SalesOrgSampleUploadActive");
    By callOffOrderCheckBox=By.id("SalesOrgOffOrder");
    By CCEOrderUpload =By.id("SalesOrgSampleUploadActive");



    By enableOrdersWithoutShade = By.id("SalesOrgEnabledOrdersWithoutShade");

    public Mst_EditSalesOrgPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
        return element;
    }

    public WebElement getNameField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(nameField));
        return element;
    }
    
    public WebElement getDescriptionField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(descriptionField));
        return element;
    }
    
    public WebElement getContractOrderField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(contractOrderField));
        return element;
    }
    
    public WebElement getSubAccountField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(subAcctField));
        return element;
    }
    
    public WebElement getSubAccountLabel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(subAcctLabel));
        return element;
    }
    
    public WebElement getMailNotificationField() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mailNotificationField));
        return element;
    }
    
    public WebElement getMailNotificationLabel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mailNotificationLabel));
        return element;
    }
    
    public WebElement getSaveButton() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(saveButton));
        return element;
    }
    
    public WebElement getCancelButton() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(cancelButton));
        return element;
    }

    public WebElement getEnableOrdersWithoutShade(){
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(enableOrdersWithoutShade));
        return element;
    }

    public Mst_SalesOrgPage pressSave() {
        WebElement btn = getSaveButton();
        btn.click();
        
        return new Mst_SalesOrgPage(driver);
    }
    
    public Mst_EditSalesOrgPage setSalesOrg(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(nameField));
        element.clear();
        
        CommonTask.setInputField(driver, nameField, item);
        return new Mst_EditSalesOrgPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement name = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(nameField));
        WebElement desc = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(descriptionField));
        WebElement contractOrder = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(contractOrderField));
        WebElement saveBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancelBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Edit Sales Org Page: Name field not displayed",name.isDisplayed());
        AssertJUnit.assertTrue("Edit Sales Org Page: Description field not displayed",desc.isDisplayed());
        AssertJUnit.assertTrue("Edit Sales Org Page: Contract Order field not displayed",contractOrder.isDisplayed());
        AssertJUnit.assertTrue("Edit Sales Org Page: Save button not displayed",saveBtn.isDisplayed());
        AssertJUnit.assertTrue("Edit Sales Org Page: Cancel button not displayed",cancelBtn.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(descriptionField));
    }

    public void enableCCEOrderUploadCheckBox(){
        CommonTask.setCheckBox(driver, orderUploadCheckBox);
    }

    public void enableCallOffOrderCheckBox(){
        CommonTask.setCheckBox(driver, callOffOrderCheckBox);
    }



    public void disableCCEOrderUploadCheckBox(){
        CommonTask.unSetCheckBox(driver, orderUploadCheckBox);
    }

    public void enableCCEOrdersWithoutShade(){
        CommonTask.setCheckBox(driver, enableOrdersWithoutShade);
    }

    public void disableCCEOrdersWithoutShade(){
        CommonTask.unSetCheckBox(driver, enableOrdersWithoutShade);
    }



    public boolean isChecked(By fieldLocator){
        if (driver.findElement(fieldLocator).isSelected()) {
            System.out.println("Flag is checked!");
            return true;
        }
        else {
            System.out.println("Flag is not checked!");
            return false;
        }
    }

    public boolean isEOwS_Checked(){
        if (driver.findElement(enableOrdersWithoutShade).isSelected()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void disableCallOffOrderCheckBox(){
        CommonTask.unSetCheckBox(driver, callOffOrderCheckBox);
    }

    public void enableCCEOrderUpload(){
        CommonTask.setCheckBox(driver, CCEOrderUpload);
    }

    public void disableCCEOrderUpload(){
        CommonTask.unSetCheckBox(driver, CCEOrderUpload);
    }

}
