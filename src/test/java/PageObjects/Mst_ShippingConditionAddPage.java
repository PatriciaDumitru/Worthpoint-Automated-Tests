package PageObjects;

import AutomationFramework.DataItems;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Stefan on 09.05.2016.
 */
public class Mst_ShippingConditionAddPage extends WBA_BasePage{
    //Locators
    By shippingConditionInput = By.cssSelector("#ShippingConditionShippingConditionName");
    By saveButton = By.cssSelector("#ShippingConditionAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#ShippingConditionAddForm > div.actions > ul > li:nth-child(2) > a");
    By flashMessage = By.cssSelector("#flashMessage");

    public Mst_ShippingConditionAddPage(WebDriver driver) {
        super(driver);
    }

    public Mst_ShippingConditionPage clickSaveButton (){
        WebElement save = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        save.click();
        try {
            System.out.println(getFlashMessage());
            driver.findElement(cancelButton).click();
            return new Mst_ShippingConditionPage(driver);
        } catch (Exception e) {
            System.out.println("Shipping Condition saved!");
            return new Mst_ShippingConditionPage(driver);
        }
    }

    public Mst_ShippingConditionPage clickCancelButton (){
        WebElement cancel = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        cancel.click();
        return new Mst_ShippingConditionPage(driver);
    }

    public void inputShippingCondition(String item){
        WebElement shipCond = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shippingConditionInput));
        shipCond.sendKeys(item);
    }

    public String getFlashMessage(){
        WebElement elem = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        return driver.findElement(flashMessage).getText();
    }

    public boolean verifyBreadCrumb(){
        WebElement breadCrumb = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(DataItems.breadcrumbLocator));
        if (breadCrumb.getText().contentEquals("Shipping Condition | Add Shipping Condition")){
            return true;
        } else
            return false;
    }

    public void waitForElement() {
        WebElement elem = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
    }
}
