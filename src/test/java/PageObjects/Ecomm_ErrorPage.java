
package PageObjects;

import AutomationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Ecomm_ErrorPage extends WBA_BasePage {
    
    //Locators
    By errorBar = By.id("flashMessage");
    By splitTextField = By.cssSelector("#flashMessage > div");
    
    public Ecomm_ErrorPage(WebDriver driver) {
        super(driver);
    }
    
    public void waitForError() {
        WebElement wait = Wait.clickable(driver,errorBar);
    }
    
    public boolean ensureErrorPage(String error) {
        String errorDisplayed = getError();
        
        return errorDisplayed.contains(error);
    }
    
    public boolean ensureOrderSplit() {
        //When an invalid shade/material is entered, the order can be split by WBA to process the available materials. This checks if the split message is displayed
        
        boolean split;
        
        try {
            WebElement element = Wait.visible(driver, splitTextField);
            split = true;
            System.out.println("Order Split");
        } catch (Exception e) {
            split = false;
            System.out.println("Order was not split");
        }
        
        return split;
        
    }
    
    public String getError() {
        return driver.findElement(errorBar).getText();
    }
    
}
