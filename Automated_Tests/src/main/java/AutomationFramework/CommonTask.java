
package AutomationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonTask {
    
    public static void setSearchField(WebDriver driver,By fieldLocator, String item) {
        By searchLocator = By.cssSelector("#select2-drop > div > input");
        By resultLocator = By.cssSelector("#select2-drop > ul > li");
        
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fieldLocator));
        
        Actions action = new Actions(driver);
        action.click(driver.findElement(fieldLocator)).build().perform();
        
        WebElement waitForSearch = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(searchLocator));
        driver.findElement(searchLocator).sendKeys(item);
        //Wait for search result to load
        Boolean waitForResult = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(resultLocator, item));
        //Press enter
        action.sendKeys(driver.findElement(searchLocator), Keys.ENTER).build().perform();
        
        //Wait for update
        boolean waitForUpdate = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(fieldLocator, item)); 
    }
    
    public static void setDropDownField(WebDriver driver,By fieldLocator,String item) throws InterruptedException {
               
        //Wait for field to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fieldLocator));
        //Click and type
        Actions action = new Actions(driver);
        action.click(driver.findElement(fieldLocator)).build().perform();
        action.sendKeys(driver.findElement(fieldLocator),item).build().perform();
        Thread.sleep(200);
        //Wait for field to update
        boolean waitForUpdate = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(fieldLocator, item));
        //Press enter
        driver.findElement(fieldLocator).sendKeys(Keys.ENTER);
        //Wait again
        boolean waitForUpdate2 = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(fieldLocator, item));
    }
    
    public static void setChoiceField(WebDriver driver, By fieldLocator, String item) {
        By resultLocator = By.cssSelector("#select2-drop > ul > li.select2-results-dept-0.select2-result.select2-result-selectable.select2-highlighted");
        //Wait for box to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fieldLocator));
        //click and enter item
        driver.findElement(fieldLocator).click();
        driver.findElement(fieldLocator).sendKeys(item);
        //Wait for result
        boolean waitForResult = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(resultLocator,item));
        
        //Press enter
        driver.findElement(fieldLocator).sendKeys(Keys.ENTER);
        
    }
    
    public static void setChoiceFieldAlt(WebDriver driver,By fieldLocator,String item){
        By resultLocator = By.cssSelector("#select2-drop > ul > li");
        
        //Wait for box to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fieldLocator));
        //click and enter item
        driver.findElement(fieldLocator).click();
        driver.findElement(fieldLocator).sendKeys(item);
        //Wait for result
        boolean waitForResult = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(resultLocator,item));
        
        //Press enter
        driver.findElement(fieldLocator).sendKeys(Keys.ENTER);
    }
    
    public static void setTextField(WebDriver driver,By fieldLocator, String item) {
    	WebElement waitForField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fieldLocator));
    	driver.findElement(fieldLocator).click();
    	driver.findElement(fieldLocator).sendKeys(item);
    	boolean waitForText = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElement(fieldLocator, item));

    }
    
    public static ExpectedCondition<Boolean> textContains(final String text, final String elementText) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                if (elementText.contains(text)) {
                    return true;
                } else {
                    return false;
                }
            }
        
        };
    }
    
    public static ExpectedCondition<Boolean> boxIsChecked(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                if (element.getAttribute("checked").equals("true")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }
    
}
