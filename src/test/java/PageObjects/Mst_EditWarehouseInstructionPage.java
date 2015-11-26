
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

public class Mst_EditWarehouseInstructionPage extends WBA_BasePage {
    
    By salesOrgField = By.id("WarehouseInstructionSalesOrgId");
    By whsInstField = By.id("WarehouseInstructionWarehouseInstructions");
    By saveButton = By.cssSelector("#WarehouseInstructionEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#WarehouseInstructionEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditWarehouseInstructionPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditWarehouseInstructionPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,salesOrgField,item);
        return new Mst_EditWarehouseInstructionPage(driver);
    }
    
    public Mst_EditWarehouseInstructionPage setWarehouseInstruction(String item) {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(whsInstField));
        element.clear();
        
        CommonTask.setInputField(driver, whsInstField, item);
        return new Mst_EditWarehouseInstructionPage(driver);
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
    
        AssertJUnit.assertTrue("Edit Warehouse Instruction Page: Sales Org Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Edit Warehouse Instruction Page: Warehouse Instruction Field not displayed",whsInst.isDisplayed());
        AssertJUnit.assertTrue("Edit Warehouse Instruction Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Edit Warehouse Instruction Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement whsInst = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(whsInstField));
    }
    
}
