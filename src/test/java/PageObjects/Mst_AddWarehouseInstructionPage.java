
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_AddWarehouseInstructionPage extends WBA_BasePage {
    
    By salesOrgField = By.id("WarehouseInstructionSalesOrgId");
    By whsInstField = By.id("WarehouseInstructionWarehouseInstructions");
    By saveButton = By.cssSelector("#WarehouseInstructionAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#WarehouseInstructionAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddWarehouseInstructionPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_AddWarehouseInstructionPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,salesOrgField,item);
        return new Mst_AddWarehouseInstructionPage(driver);
    }
    
    public Mst_AddWarehouseInstructionPage setWarehouseInstruction(String item) {
        CommonTask.setInputField(driver, whsInstField, item);
        return new Mst_AddWarehouseInstructionPage(driver);
    }
    
    public Mst_WarehouseInstructionsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_WarehouseInstructionsPage(driver);
    }
    
    public void checkFields() {
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement whsInst = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(whsInstField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
    
        AssertJUnit.assertTrue("Add Warehouse Instruction Page: Sales Org Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Warehouse Instruction Page: Warehouse Instruction Field not displayed",whsInst.isDisplayed());
        AssertJUnit.assertTrue("Add Warehouse Instruction Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Warehouse Instruction Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement whsInst = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(whsInstField));
    }

}
