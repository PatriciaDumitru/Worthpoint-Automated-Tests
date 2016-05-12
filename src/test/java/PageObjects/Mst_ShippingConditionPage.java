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
    By table = By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr");//*[@id="content"]/div[3]/table/tbody/tr[1]

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

    //public

    public void inputShippingCondition(String item){
        WebElement shipCond = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shippinpConditionInput));
        shipCond.sendKeys(item);
    }

    public Mst_ShippingConditionAddPage clickNewShippingCond (){
        WebElement newShip = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newShippingCondButton));
        newShip.click();
        return new Mst_ShippingConditionAddPage(driver);
    }

    public boolean searchShipCondInTable(String item){
        WebElement newShip = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newShippingCondButton));
        int cond =0;
        int lines = driver.findElements(table).size();
        System.out.println("No of Shipping conditions:"+lines);
        for (int i=1;i<lines;i++){
            WebElement ship = driver.findElement(By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+(i+1)+") > td:nth-child(2)"));
            System.out.println("Shipping Condition on row:"+i+" is:"+ship.getText());
            if (ship.getText().contentEquals(item)){
                System.out.println("Shipping Condition:"+item+" was found on row:"+i);
                cond = cond+1;
                System.out.println("Cond:"+cond);
                break;
            } else{
                System.out.println("Shipping Condition:"+item+" was not found!");
            }
        }
        if (cond==0){
            return false;
        } else
            return true;
    }

    public void waitForElement() {
        WebElement shipToPartyName = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
    }
}
