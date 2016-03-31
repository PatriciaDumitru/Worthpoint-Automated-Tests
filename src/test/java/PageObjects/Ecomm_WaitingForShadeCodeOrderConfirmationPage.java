package PageObjects;

import AutomationFramework.CommonTask;
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

/**
 * Created by Stefan on 30.03.2016.
 */
public class Ecomm_WaitingForShadeCodeOrderConfirmationPage extends WBA_BasePage{

    //Locators
    By flashMessage = By.cssSelector("#flashMessage");
    By confirmButton = By.cssSelector("#submit1");
    By cancelButton = By.cssSelector("#cancel1");

    //table locators


    public Ecomm_WaitingForShadeCodeOrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getFlashMessage(){
        String text = driver.findElement(flashMessage).getText();
        return text;
    }

    public Ecomm_OrderInformationPage pressEdit(int row) {
        By editButton = By.cssSelector("#remove_"+row+" > td:nth-child(2) > a > span");//#remove_0 > td:nth-child(2) > a > span
        WebElement edit = Wait.clickable(driver,editButton);
        edit.click();
        return  new Ecomm_OrderInformationPage(driver);
    }

    public Ecomm_OutstandingOrdersPage pressConfirm() {
        WebElement confirm = Wait.clickable(driver,confirmButton);
        confirm.click();

        try {
            Alert alert1 = Wait.alert(driver);
            System.out.println("Alert appeared: " + alert1.getText());
            alert1.accept();
        } catch (Exception e) {

        }
        try {
            Alert alert2 = Wait.alert(driver);
            System.out.println("Alert appeared: " + alert2.getText());
            alert2.accept();
        } catch (Exception e) {

        }
        return new Ecomm_OutstandingOrdersPage(driver);
    }

    public void waitForElement() {
        WebElement waitForCancelBtn = Wait.clickable(driver,cancelButton);
    }

}
