
package PageObjects;

import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.Alert;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//To reach page, go to Shade Not Available, and click edit for a record. Shade Not Available Confirmation Page should appear
public class Ecomm_ShadeOrderConfirmationPage extends WBA_BasePage {
    
    //Locators
    By editButton = By.cssSelector("#remove_0 > td:nth-child(1) > a > span");
    By backButton = By.id("backLink");
    By cancelButton = By.id("cancel1");
    By sendForApprovalButton = By.cssSelector("#BulkOrderShadenotavailableConfirmForm > div:nth-child(7) > div:nth-child(3) > input");
    By submitButton = By.id("submit1");
    By flashMessage = By.id("flashMessage");
    
    public Ecomm_ShadeOrderConfirmationPage(WebDriver driver) {
        super(driver);
    }
    
    public Ecomm_ShadeNotAvailablePage pressBack() {
        WebElement back = Wait.clickable(driver,backButton);
        back.click();
        return new Ecomm_ShadeNotAvailablePage(driver);
    }
    
    public Ecomm_ShadeNotAvailablePage pressCancel() {
        WebElement cancel = Wait.clickable(driver,cancelButton);
        cancel.click();
        return new Ecomm_ShadeNotAvailablePage(driver);
    }
    
    public Ecomm_OrderInformationPage pressEdit() {
        WebElement edit = Wait.clickable(driver,editButton);
        edit.click();
        return new Ecomm_OrderInformationPage(driver);
    }
    
    public Ecomm_PendingApprovalListPage pressSend() {
        WebElement sendForApproval = Wait.clickable(driver,sendForApprovalButton);
        sendForApproval.click();
        return new Ecomm_PendingApprovalListPage(driver);
    }
    
    public Ecomm_OutstandingOrdersPage pressSubmit() {
        WebElement submit = Wait.clickable(driver,submitButton);
        submit.click();
        
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        try {
            Alert alert2 = Wait.alert(driver);
            System.out.println("Alert appeared: " + alert2.getText());
            alert2.accept();
        } catch (Exception e) {
           
        }
        
        return new Ecomm_OutstandingOrdersPage(driver);
    }

    public String getFlashMessage(){
        String text = driver.findElement(flashMessage).getText();
        return text;
    }
    
    public void checkFields() {
        WebElement edit = Wait.clickable(driver,editButton);
        WebElement back = Wait.clickable(driver,backButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        AssertJUnit.assertTrue("Shade Not Available Order Confirmation Page: Edit button not displayed correctly",edit.isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Order Confirmation Page: Back button not displayed correctly",back.isDisplayed());
        AssertJUnit.assertTrue("Shade Not Available Order Confirmation Page: Cancel button not displayed correctly",cancel.isDisplayed());      
    }
    
}
