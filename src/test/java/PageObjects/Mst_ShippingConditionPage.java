package PageObjects;

import AutomationFramework.DataItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Stefan on 06.05.2016.
 */
public class Mst_ShippingConditionPage extends WBA_BasePage{
    //Locators
    By breadcrumb = By.cssSelector("#content > h2");
    By shippinpConditionInput = By.cssSelector("#filterShippingConditionName");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");

    //table locators

    //Bottom buttons locators
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newShippingCondButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");

    public Mst_ShippingConditionPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getBreadcrumb(){
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }

    public void waitForElement() {
        WebElement shipToPartyName = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
    }
}
