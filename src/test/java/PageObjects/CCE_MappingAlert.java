package PageObjects;

import AutomationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by dion on 31.03.2016.
 */
//This class is used after "Upload" is pressed in CCE > Order File Upload and displays the question "Do you want to go with existing Mapping?"
public class CCE_MappingAlert {

    WebDriver driver;

    //Locators
    By noButton = By.xpath("//*[@id='msgBox1459414864783Buttons']/input[2]");
    By yesButton = By.xpath("//input[@value='Yes']");

    public CCE_MappingAlert(WebDriver passedDriver) {
        driver = passedDriver;
    }


    public CCE_MappingPage pressYes() {
        //Wait for button to be clickable
        WebElement btn = Wait.clickable(driver, yesButton);
        //New action to click yes
        Actions clickYes = new Actions(driver);
        clickYes.click(btn).build().perform();

        return new CCE_MappingPage(driver);
    }

    public CCE_MappingPage pressNo() {
        //Wait for button to be clickable
        WebElement btn = Wait.clickable(driver, noButton);
        //New action to click no
        Actions clickNo = new Actions(driver);
        clickNo.click(btn).build().perform();

        return new CCE_MappingPage(driver);
    }

}
