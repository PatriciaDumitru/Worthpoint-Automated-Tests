
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
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
        return Wait.visible(driver,DataItems.breadcrumbLocator);
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
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_WarehouseInstructionsPage(driver);
    }
    
    public void checkFields() {
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement whsInst = Wait.clickable(driver,whsInstField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
    
        AssertJUnit.assertTrue("Add Warehouse Instruction Page: Sales Org Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Warehouse Instruction Page: Warehouse Instruction Field not displayed",whsInst.isDisplayed());
        AssertJUnit.assertTrue("Add Warehouse Instruction Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Warehouse Instruction Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement whsInst = Wait.clickable(driver,whsInstField);
    }

}
